package su.itpro.photogram.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.dao.ProfileDao;
import su.itpro.photogram.datasource.DataSource;
import su.itpro.photogram.exception.DaoException;
import su.itpro.photogram.model.entity.Profile;
import su.itpro.photogram.model.enums.Gender;
import su.itpro.photogram.util.converter.ContactConverter;

public class ProfileDaoImpl implements ProfileDao {

  private static final ProfileDao INSTANCE = new ProfileDaoImpl();

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
      prepared.setString(3, (profile.getGender() != null)
                            ? profile.getGender().toString() : null);
      prepared.setDate(4, (profile.getBirthdate() != null)
                          ? Date.valueOf(profile.getBirthdate()) : null);
      prepared.setObject(5, ContactConverter.toJson(profile.getContacts()));
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
      prepared.setString(2, (profile.getGender() != null)
                            ? profile.getGender().toString() : null);
      LocalDate birthdate = profile.getBirthdate();
      prepared.setDate(3, (birthdate != null) ? Date.valueOf(birthdate) : null);
      prepared.setObject(4, ContactConverter.toJson(profile.getContacts()));
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
    Profile profile = new Profile(resultSet.getObject("account_id", UUID.class));
    String gender = resultSet.getString("gender");
    if (gender != null) {
      profile.setGender(Gender.valueOf(gender));
    }
    profile.setFullName(resultSet.getString("full_name"));
    Date birthDate = resultSet.getDate("birthdate");
    if (birthDate != null) {
      profile.setBirthdate(birthDate.toLocalDate());
    }
    profile.setContacts(ContactConverter.toMap(resultSet.getString("contacts")));
    profile.setAboutMe(resultSet.getString("about_me"));
    return profile;
  }
}
