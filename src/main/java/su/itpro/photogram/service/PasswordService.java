package su.itpro.photogram.service;

import su.itpro.photogram.model.dto.ChangePasswordDto;

/**
 * Интерфейс смены пароля
 */
public interface PasswordService {

  /**
   * Метод изменения пароля для входа в ЛК
   *
   * @param dto - содержит информацию о текущем и новом паролях
   */
  void changePassword(ChangePasswordDto dto);

}
