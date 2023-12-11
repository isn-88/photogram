package su.itpro.photogram.model.dto;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.model.enums.Role;
import su.itpro.photogram.model.enums.Status;

public record AccountDto(UUID id,
                         String phone,
                         String email,
                         String username,
                         Role role,
                         Status status,
                         Boolean isVerifiedPhone,
                         Boolean isVerifiedEmail,
                         Instant createDate) {

  public static AccountDto of(Account account) {
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
