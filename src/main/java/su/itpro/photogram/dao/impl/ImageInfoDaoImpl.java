package su.itpro.photogram.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.dao.ImageInfoDao;
import su.itpro.photogram.dao.exception.DaoException;
import su.itpro.photogram.datasource.DataSource;
import su.itpro.photogram.model.entity.Image;

public class ImageInfoDaoImpl implements ImageInfoDao {

  private static final String ID = "id";
  private static final String ACCOUNT_ID = "account_id";
  private static final String POST_ID = "post_id";
  private static final String FILE_NAME = "file_name";
  private static final String ORDINAL = "ordinal";


  private static final String FIND_PREVIEW_IMAGE_ID_SQL = """
      SELECT id
      FROM image
      WHERE post_id = ?
      ORDER BY ordinal
      LIMIT 1
      ;
      """;

  private static final String FIND_ALL_BY_POST_ID_SQL = """
      SELECT id, account_id, post_id, file_name, ordinal
      FROM image
      WHERE post_id = ?
      ORDER BY ordinal
      ;
      """;

  private static final String SAVE_SQL = """
      INSERT INTO image (id, account_id, post_id, file_name, ordinal)
      VALUES (?, ?, ?, ?, ?)
      ;
      """;

  private static final String UPDATE_SQL = """
      UPDATE image
      SET post_id = ?,
          file_name = ?,
          ordinal = ?
      WHERE id = ?
      ;
      """;

  private static final ImageInfoDao INSTANCE = new ImageInfoDaoImpl();


  private ImageInfoDaoImpl() {
  }

  public static ImageInfoDao getInstance() {
    return INSTANCE;
  }

  @Override
  public Optional<Image> findById(UUID id) {
    return Optional.empty();
  }

  @Override
  public List<Image> findAll() {
    // TODO add implementation
    return new ArrayList<>();
  }

  @Override
  public List<Image> findAllByPostId(UUID postId) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(FIND_ALL_BY_POST_ID_SQL)) {
      prepared.setObject(1, postId);

      prepared.executeQuery();
      var resultSet = prepared.getResultSet();

      List<Image> images = new ArrayList<>();
      while (resultSet.next()) {
        images.add(parseImage(resultSet));
      }
      return images;
    } catch (SQLException e) {
      throw new DaoException("Error findAllByPostId", e.getMessage());
    }
  }

  @Override
  public Optional<UUID> findPreviewImageId(UUID postId) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(FIND_PREVIEW_IMAGE_ID_SQL)) {
      prepared.setObject(1, postId);

      prepared.executeQuery();
      var resultSet = prepared.getResultSet();

      UUID imageId = null;
      if (resultSet.next()) {
        imageId = resultSet.getObject(ID, UUID.class);
      }
      return Optional.ofNullable(imageId);
    } catch (SQLException e) {
      throw new DaoException("Error findPreviewImageIds", e.getMessage());
    }
  }

  @Override
  public Image save(Image image) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
      prepared.setObject(1, image.getId());
      prepared.setObject(2, image.getAccountId());
      prepared.setObject(3, image.getPostId());
      prepared.setString(4, image.getFileName());
      prepared.setInt(5, image.getOrdinal());
      prepared.executeUpdate();

      var generatedKeys = prepared.getGeneratedKeys();
      if (generatedKeys.next()) {
        image.setId(generatedKeys.getObject(1, UUID.class));
      }
    } catch (SQLException e) {
      throw new DaoException("Error save Image", e.getMessage());
    }
    return image;
  }

  @Override
  public void update(Image image) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(UPDATE_SQL)) {
      prepared.setObject(1, image.getPostId());
      prepared.setString(2, image.getFileName());
      prepared.setInt(3, image.getOrdinal());
      prepared.setObject(4, image.getId());

      prepared.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException("Error update Image", e.getMessage());
    }
  }

  @Override
  public void delete(UUID id) {
    // TODO add implementation
  }

  private Image parseImage(ResultSet resultSet) throws SQLException {
    Image image = new Image(resultSet.getObject(ID, UUID.class));
    image.setAccountId(resultSet.getObject(ACCOUNT_ID, UUID.class));
    image.setPostId(resultSet.getObject(POST_ID, UUID.class));
    image.setFileName(resultSet.getString(FILE_NAME));
    image.setOrdinal(resultSet.getShort(ORDINAL));
    return image;
  }

}
