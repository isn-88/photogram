package su.itpro.photogram.mapper;

import su.itpro.photogram.model.dto.ProfileCreateDto;
import su.itpro.photogram.model.entity.Profile;

public class ProfileCreateMapper implements Mapper<ProfileCreateDto, Profile> {

  private static final ProfileCreateMapper INSTANCE = new ProfileCreateMapper();


  private ProfileCreateMapper() {
  }

  public static ProfileCreateMapper getInstance() {
    return INSTANCE;
  }

  @Override
  public Profile mapFrom(ProfileCreateDto dto) {
    Profile profile = new Profile();
    profile.setFullName(dto.fullName());
    profile.setGender(dto.gender());
    return profile;
  }

}
