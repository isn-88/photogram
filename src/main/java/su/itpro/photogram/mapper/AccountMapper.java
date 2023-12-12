package su.itpro.photogram.mapper;

import java.util.Objects;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.RegistrationDto;
import su.itpro.photogram.model.entity.Account;

public class AccountMapper {

  private static final AccountMapper INSTANCE = new AccountMapper();


  private AccountMapper() {
  }

  public static AccountMapper getInstance() {
    return INSTANCE;
  }


  public Account mapToAccount(RegistrationDto dto) {
    return new Account(dto.phone(), dto.email(), dto.username(), dto.password());
  }


  public AccountDto mapToAccountDto(Account account) {
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
