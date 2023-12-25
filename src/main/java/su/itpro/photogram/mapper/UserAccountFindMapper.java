package su.itpro.photogram.mapper;

import java.util.List;
import su.itpro.photogram.model.dto.AccountFindDto;
import su.itpro.photogram.model.dto.UserAccountFindDto;
import su.itpro.photogram.model.enums.Role;
import su.itpro.photogram.model.enums.Status;

public class UserAccountFindMapper implements Mapper<UserAccountFindDto, AccountFindDto> {

  private static final UserAccountFindMapper INSTANCE = new UserAccountFindMapper();


  private UserAccountFindMapper() {
  }

  public static UserAccountFindMapper getInstance() {
    return INSTANCE;
  }

  @Override
  public AccountFindDto mapFrom(UserAccountFindDto dto) {
    return new AccountFindDto(
        null,
        dto.username(),
        null,
        null,
        List.of(Status.ACTIVE),
        List.of(Role.USER)
    );
  }
}
