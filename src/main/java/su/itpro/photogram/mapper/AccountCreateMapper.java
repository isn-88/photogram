package su.itpro.photogram.mapper;

import java.util.Objects;
import su.itpro.photogram.model.dto.AccountChangeDto;
import su.itpro.photogram.model.entity.Account;

public class AccountCreateMapper implements Mapper<AccountChangeDto, Account> {

  private static final AccountCreateMapper INSTANCE = new AccountCreateMapper();

  private AccountCreateMapper() {
  }

  public static AccountCreateMapper getInstance() {
    return INSTANCE;
  }

  @Override
  public Account mapFrom(AccountChangeDto changeDto) {
    if (Objects.isNull(changeDto)) {
      return null;
    }
    return new Account(changeDto.phone(),
                       changeDto.email(),
                       changeDto.username(),
                       changeDto.password());
  }

}
