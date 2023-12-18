package su.itpro.photogram.mapper;

import su.itpro.photogram.model.dto.CreateAccountDto;
import su.itpro.photogram.model.entity.Account;

public class AccountCreateMapper implements Mapper<CreateAccountDto, Account> {

  private static final AccountCreateMapper INSTANCE = new AccountCreateMapper();

  private AccountCreateMapper() {
  }

  public static AccountCreateMapper getInstance() {
    return INSTANCE;
  }

  @Override
  public Account mapFrom(CreateAccountDto dto) {
    String phone = dto.phone().replaceAll("\\D", "");
    return new Account(phone, dto.email(), dto.username(), dto.password());
  }

}
