package su.itpro.photogram.mapper;

import java.util.Objects;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.entity.Account;

public class AccountDtoMapper implements Mapper<Account, AccountDto> {

  private static final AccountDtoMapper INSTANCE = new AccountDtoMapper();


  private AccountDtoMapper() {
  }

  public static AccountDtoMapper getInstance() {
    return INSTANCE;
  }

  @Override
  public AccountDto mapFrom(Account account) {
    if (Objects.isNull(account)) {
      return null;
    }
    return new AccountDto(
        account.getId(),
        account.getPhone(),
        account.getEmail(),
        account.getUsername(),
        account.getRole(),
        account.getStatus(),
        account.getVerifiedPhone(),
        account.getVerifiedEmail(),
        account.getCreateDate()
    );
  }

}
