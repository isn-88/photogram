package su.itpro.photogram.dao.impl;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.dao.ImageDao;
import su.itpro.photogram.datasource.DataSource;
import su.itpro.photogram.exception.DaoException;
import su.itpro.photogram.model.entity.Image;

public class ImageDaoImpl implements ImageDao {

  private static final String ID = "id";

  private static final String POST_ID = "post_id";


  private static final String FIND_PREVIEW_IMAGE_SQL = """
      SELECT id
      FROM image
      WHERE post_id = ? AND ordinal = 1
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

  private static final ImageDao INSTANCE = new ImageDaoImpl();


  private ImageDaoImpl() {
  }

  public static ImageDao getInstance() {
    return INSTANCE;
  }

  @Override
  public Optional<Image> findById(UUID id) {
    return Optional.empty();
  }

  @Override
  public List<Image> findAll() {
    return null;
  }

  @Override
  public UUID findPreviewImageId(UUID postId) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(FIND_PREVIEW_IMAGE_SQL)) {
      prepared.setObject(1, postId);

      prepared.executeQuery();
      var resultSet = prepared.getResultSet();

      UUID imageId = null;
      if (resultSet.next()) {
        imageId = resultSet.getObject(ID, UUID.class);
      }
      return imageId;
    } catch (SQLException e) {
      throw new DaoException("Error findPreviewImageIds", e.getMessage());
    }
  }

  @Override
  public void save(Image image) {
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
  public boolean delete(UUID id) {
    return false;
  }

}
