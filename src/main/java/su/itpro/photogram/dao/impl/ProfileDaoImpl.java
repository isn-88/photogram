package su.itpro.photogram.dao.impl;

import static su.itpro.photogram.util.converter.ContactConverter.toJson;
import static su.itpro.photogram.util.converter.ContactConverter.toMap;
import static su.itpro.photogram.util.converter.DateConverter.fromSqlDate;
import static su.itpro.photogram.util.converter.DateConverter.toSqlDate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.dao.ProfileDao;
import su.itpro.photogram.datasource.DataSource;
import su.itpro.photogram.exception.dao.DaoException;
import su.itpro.photogram.model.entity.Profile;
import su.itpro.photogram.model.enums.Gender;

public class ProfileDaoImpl implements ProfileDao {

  private static final ProfileDao INSTANCE = new ProfileDaoImpl();

  private static final String ACCOUNT_ID = "account_id";
  private static final String FULL_NAME = "full_name";
  private static final String GENDER = "gender";
  private static final String BIRTHDATE = "birthdate";
  private static final String CONTACTS = "contacts";
  private static final String ABOUT_ME = "about_me";

  private static final String FIND_BY_ID_SQL = """
      SELECT account_id, full_name, gender, birthdate, contacts, about_me
      FROM profile p
          JOIN gender g on g.id = p.gender_id
      WHERE account_id = ?
      ;
      """;

  private static final String SAVE_SQL = """
      INSERT INTO profile (
          account_id,
          full_name,
          birthdate,
          gender_id,
          contacts,
          about_me
          )
      VALUES (?, ?, ?, (SELECT id FROM gender WHERE gender = ?), ?::JSON, ?)
      ;
      """;

  private static final String UPDATE_SQL = """
      UPDATE profile
      SET full_name = ?,
          gender_id = (SELECT id FROM gender WHERE gender = ?),
          birthdate = ?,
          contacts = ?::JSON,
          about_me = ?
      WHERE account_id = ?
      ;
      """;

  private ProfileDaoImpl() {
  }

  public static ProfileDao getInstance() {
    return INSTANCE;
  }

  @Override
  public Optional<Profile> findById(UUID id) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(FIND_BY_ID_SQL)) {
      prepared.setObject(1, id);

      prepared.executeQuery();

      Profile profile = null;
      var resultSet = prepared.getResultSet();
      if (resultSet.next()) {
        profile = parsePerson(resultSet);
      }
      return Optional.ofNullable(profile);
    } catch (SQLException e) {
      throw new DaoException("Error findById Person", e.getMessage());
    }
  }

  @Override
  public List<Profile> findAll() {
    // TODO add implementation
    return new ArrayList<>();
  }

  @Override
  public Profile save(Profile profile) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(SAVE_SQL)) {
      prepared.setObject(1, profile.getAccountId());
      prepared.setString(2, profile.getFullName());
      prepared.setDate(3, toSqlDate(profile.getBirthdate()));
      prepared.setString(4, profile.getGender().name());
      prepared.setObject(5, toJson(profile.getContacts()));
      prepared.setString(6, profile.getAboutMe());
      prepared.executeUpdate();

    } catch (SQLException e) {
      throw new DaoException("Error save Person", e.getMessage());
    }
    return profile;
  }

  @Override
  public boolean update(Profile profile) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(UPDATE_SQL)) {
      prepared.setString(1, profile.getFullName());
      prepared.setString(2, profile.getGender().name());
      prepared.setDate(3, toSqlDate(profile.getBirthdate()));
      prepared.setObject(4, toJson(profile.getContacts()));
      prepared.setString(5, profile.getAboutMe());
      prepared.setObject(6, profile.getAccountId());

      return prepared.executeUpdate() > 0;
    } catch (SQLException e) {
      throw new DaoException("Error update Person", e.getMessage());
    }
  }

  @Override
  public void delete(UUID id) {
    // Profile is not deleting
  }

  private Profile parsePerson(ResultSet resultSet) throws SQLException {
    Profile profile = new Profile(resultSet.getObject(ACCOUNT_ID, UUID.class));
    fromSqlDate(resultSet.getDate(BIRTHDATE)).ifPresent(profile::setBirthdate);
    profile.setGender(Gender.valueOf(resultSet.getString(GENDER)));
    profile.setFullName(resultSet.getString(FULL_NAME));
    profile.setContacts(toMap(resultSet.getString(CONTACTS)));
    profile.setAboutMe(resultSet.getString(ABOUT_ME));
    return profile;
  }

}
