package su.itpro.photogram.service;

import su.itpro.photogram.model.entity.Account;

public interface AccountService {

  Account registerNewAccount(String phone, String email, String username, String password);

  void updatePassword(Account account, String newPassword);

  Account findByUsername(String username);

  Account findByEmail(String email);

  Account findByPhone(String phone);

  Account update(String username, String newPhone, String newEmail, String newUsername);

}
