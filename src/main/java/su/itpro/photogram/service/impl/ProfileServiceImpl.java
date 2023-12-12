package su.itpro.photogram.service.impl;

import static su.itpro.photogram.util.ServiceUtil.optionalOf;

import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.dao.ProfileDao;
import su.itpro.photogram.exception.service.ProfileServiceException;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.mapper.ProfileMapper;
import su.itpro.photogram.model.dto.ProfileDto;
import su.itpro.photogram.model.dto.ProfileEditDto;
import su.itpro.photogram.model.entity.Profile;
import su.itpro.photogram.service.ProfileService;

public class ProfileServiceImpl implements ProfileService {

  private static final ProfileServiceImpl INSTANCE = new ProfileServiceImpl();

  private final ProfileDao profileDao;
  private final ProfileMapper profileMapper;


  private ProfileServiceImpl() {
    profileDao = DaoFactory.INSTANCE.getProfileDao();
    profileMapper = ProfileMapper.getInstance();
  }

  public static ProfileServiceImpl getInstance() {
    return INSTANCE;
  }

  public ProfileDto loadProfile(UUID accountId) {
    return profileMapper.mapToProfile(profileDao.findById(accountId).orElseThrow(
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

    profileDao.update(profile);
    return profileMapper.mapToProfile(profile);
  }

}
