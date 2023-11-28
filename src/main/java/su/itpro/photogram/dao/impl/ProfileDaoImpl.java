package su.itpro.photogram.dao.impl;

import static su.itpro.photogram.util.converter.ContactConverter.toJson;
import static su.itpro.photogram.util.converter.ContactConverter.toMap;
import static su.itpro.photogram.util.converter.DateConverter.fromSqlDate;
import static su.itpro.photogram.util.converter.DateConverter.toSqlDate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.dao.ProfileDao;
import su.itpro.photogram.datasource.DataSource;
import su.itpro.photogram.exception.DaoException;
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
      FROM profile
      WHERE account_id = ?
      ;
      """;

  private static final String SAVE_SQL = """
      INSERT INTO profile (account_id, full_name, gender, birthdate, contacts, about_me)
      VALUES (?, ?, ?, ?, ?::JSON, ?)
      ;
      """;

  private static final String UPDATE_SQL = """
      UPDATE profile
      SET full_name = ?,
          gender = ?,
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
  public Optional<Profile> findById(UUID accountId) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(FIND_BY_ID_SQL)) {
      prepared.setObject(1, accountId);

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
    return null;
  }


  @Override
  public void save(Profile profile) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(SAVE_SQL)) {

      prepared.setObject(1, profile.getAccountId());
      prepared.setString(2, profile.getFullName());
      prepared.setString(3, genderToString(profile.getGender()));
      prepared.setDate(4, toSqlDate(profile.getBirthdate()));
      prepared.setObject(5, toJson(profile.getContacts()));
      prepared.setString(6, profile.getAboutMe());
      prepared.executeUpdate();

    } catch (SQLException e) {
      throw new DaoException("Error save Person", e.getMessage());
    }
  }

  @Override
  public void update(Profile profile) {
    try (var connection = DataSource.getConnection();
        var prepared = connection.prepareStatement(UPDATE_SQL)) {
      prepared.setString(1, profile.getFullName());
      prepared.setString(2, genderToString(profile.getGender()));
      prepared.setDate(3, toSqlDate(profile.getBirthdate()));
      prepared.setObject(4, toJson(profile.getContacts()));
      prepared.setString(5, profile.getAboutMe());
      prepared.setObject(6, profile.getAccountId());

      prepared.executeUpdate();
    } catch (SQLException e) {
      throw new DaoException("Error update Person", e.getMessage());
    }
  }

  @Override
  public boolean delete(UUID id) {
    return false;
  }

  private Profile parsePerson(ResultSet resultSet) throws SQLException {
    Profile profile = new Profile(resultSet.getObject(ACCOUNT_ID, UUID.class));
    genderFromString(resultSet.getString(GENDER)).ifPresent(profile::setGender);
    profile.setFullName(resultSet.getString(FULL_NAME));
    fromSqlDate(resultSet.getDate(BIRTHDATE)).ifPresent(profile::setBirthdate);
    profile.setContacts(toMap(resultSet.getString(CONTACTS)));
    profile.setAboutMe(resultSet.getString(ABOUT_ME));
    return profile;
  }

  private static String genderToString(Gender gender) {
    return Optional.ofNullable(gender)
        .map(Enum::toString)
        .orElse(null);
  }

  private static Optional<Gender> genderFromString(String gender) {
    if (gender != null && !gender.isBlank()) {
      return Optional.of(Gender.valueOf(gender));
    }
    return Optional.empty();
  }
}
