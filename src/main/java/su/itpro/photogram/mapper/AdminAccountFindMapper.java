package su.itpro.photogram.mapper;

import java.util.Arrays;
import java.util.List;
import su.itpro.photogram.model.dto.AccountFindDto;
import su.itpro.photogram.model.dto.AdminAccountFindDto;
import su.itpro.photogram.model.enums.Role;
import su.itpro.photogram.model.enums.Status;

public class AdminAccountFindMapper implements Mapper<AdminAccountFindDto, AccountFindDto> {

  private static final AdminAccountFindMapper INSTANCE = new AdminAccountFindMapper();


  private AdminAccountFindMapper() {
  }

  public static AdminAccountFindMapper getInstance() {
    return INSTANCE;
  }

  @Override
  public AccountFindDto mapFrom(AdminAccountFindDto dto) {
    return new AccountFindDto(
        dto.accountId(),
        dto.username(),
        dto.phone(),
        dto.email(),
        (dto.onlyBlocked()) ? List.of(Status.BLOCKED) : List.of(Status.ACTIVE, Status.BLOCKED),
        Arrays.asList(Role.values())
    );
  }

}

