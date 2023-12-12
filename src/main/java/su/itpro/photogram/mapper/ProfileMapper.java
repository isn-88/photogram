package su.itpro.photogram.mapper;

import java.util.Objects;
import su.itpro.photogram.model.dto.ProfileDto;
import su.itpro.photogram.model.dto.RegistrationDto;
import su.itpro.photogram.model.entity.Profile;
import su.itpro.photogram.model.enums.Gender;

public class ProfileMapper {

  private static final ProfileMapper INSTANCE = new ProfileMapper();


  private ProfileMapper() {
  }

  public static ProfileMapper getInstance() {
    return INSTANCE;
  }


  public Profile mapToProfile(RegistrationDto dto) {
    Profile profile = new Profile();
    profile.setFullName(dto.fullName());
    profile.setGender(Gender.UNDEFINE);
    return profile;
  }

  public ProfileDto mapToProfile(Profile profile) {
    if (Objects.isNull(profile)) {
      return null;
    }
    return new ProfileDto(
        profile.getAccountId(),
        profile.getFullName(),
        profile.getGender(),
        profile.getBirthdate(),
        profile.getAboutMe()
    );
  }

}
