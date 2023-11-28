package su.itpro.photogram.service;

import su.itpro.photogram.model.entity.Account;

public interface LoginService {

  Account login(String login, String password);

}
