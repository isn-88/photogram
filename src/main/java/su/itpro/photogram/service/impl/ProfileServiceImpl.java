package su.itpro.photogram.service.impl;

import static su.itpro.photogram.util.ServiceUtil.optionalOf;

import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.dao.ProfileDao;
import su.itpro.photogram.exception.service.PostServiceException;
import su.itpro.photogram.exception.service.ProfileServiceException;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.mapper.ProfileDtoMapper;
import su.itpro.photogram.model.dto.ProfileDto;
import su.itpro.photogram.model.dto.ProfileEditDto;
import su.itpro.photogram.model.entity.Profile;
import su.itpro.photogram.service.ProfileService;

public class ProfileServiceImpl implements ProfileService {

  private static final ProfileServiceImpl INSTANCE = new ProfileServiceImpl();

  private final ProfileDao profileDao;
  private final ProfileDtoMapper profileDtoMapper;


  private ProfileServiceImpl() {
    profileDao = DaoFactory.INSTANCE.getProfileDao();
    profileDtoMapper = ProfileDtoMapper.getInstance();
  }

  public static ProfileServiceImpl getInstance() {
    return INSTANCE;
  }

  public ProfileDto loadProfile(UUID accountId) {
    return profileDtoMapper.mapToProfile(profileDao.findById(accountId).orElseThrow(
        () -> new ProfileServiceException("Profile not found")
    ));
  }

  @Override
  public ProfileDto update(ProfileEditDto dto) {
    Profile profile = profileDao.findById(dto.id()).orElseThrow(
        () -> new ProfileServiceException("Profile not found")
    );
    optionalOf(dto.fullName()).ifPresent(profile::setFullName);
    Optional.ofNullable(dto.birthdate()).ifPresent(profile::setBirthdate);
    Optional.ofNullable(dto.gender()).ifPresent(profile::setGender);
    Optional.ofNullable(dto.aboutMe()).ifPresent(profile::setAboutMe);

    if (!profileDao.update(profile)) {
      throw new PostServiceException("Profile (%s) was not updated".formatted(dto.id()));
    }
    return profileDtoMapper.mapToProfile(profile);
  }

}
