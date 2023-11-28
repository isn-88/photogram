package su.itpro.photogram.service;

import java.util.UUID;
import su.itpro.photogram.model.entity.Profile;

public interface ProfileService {


  Profile registerNewProfile(UUID accountId, String fullName);

  Profile loadProfile(UUID accountId);

  void update(String username, String fullName);

}
