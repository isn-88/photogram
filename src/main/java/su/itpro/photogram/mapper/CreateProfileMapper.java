package su.itpro.photogram.mapper;

import su.itpro.photogram.model.dto.RegistrationDto;
import su.itpro.photogram.model.entity.Profile;
import su.itpro.photogram.model.enums.Gender;

public class CreateProfileMapper implements Mapper<RegistrationDto, Profile> {

  private static final CreateProfileMapper INSTANCE = new CreateProfileMapper();


  private CreateProfileMapper() {
  }

  public static CreateProfileMapper getInstance() {
    return INSTANCE;
  }

  @Override
  public Profile map(RegistrationDto dto) {
    Profile profile = new Profile();
    profile.setFullName(dto.fullName());
    profile.setGender(Gender.UNDEFINE);
    return profile;
  }
}
