package su.itpro.photogram.mapper;

import su.itpro.photogram.model.dto.RegistrationDto;
import su.itpro.photogram.model.entity.Account;

public class CreateAccountMapper implements Mapper<RegistrationDto, Account> {

  private static final CreateAccountMapper INSTANCE = new CreateAccountMapper();


  private CreateAccountMapper() {
  }

  public static CreateAccountMapper getInstance() {
    return INSTANCE;
  }

  @Override
  public Account map(RegistrationDto dto) {
    return new Account(dto.phone(), dto.email(), dto.username(), dto.password());
  }
}
