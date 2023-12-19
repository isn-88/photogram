package su.itpro.photogram.mapper;

import java.util.Objects;
import su.itpro.photogram.model.dto.ProfileDto;
import su.itpro.photogram.model.entity.Profile;

public class ProfileDtoMapper {

  private static final ProfileDtoMapper INSTANCE = new ProfileDtoMapper();


  private ProfileDtoMapper() {
  }

  public static ProfileDtoMapper getInstance() {
    return INSTANCE;
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
