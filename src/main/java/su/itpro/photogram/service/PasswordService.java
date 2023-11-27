package su.itpro.photogram.service;

public interface PasswordService {

  void changePassword(String username, String currentPassword,
                      String newPassword, String checkPassword);


}
