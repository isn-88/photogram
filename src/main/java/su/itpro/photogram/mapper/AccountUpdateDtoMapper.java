package su.itpro.photogram.mapper;

import java.util.Objects;
import su.itpro.photogram.model.dto.AccountChangeDto;
import su.itpro.photogram.model.dto.AccountUpdateDto;

public class AccountUpdateDtoMapper implements Mapper<AccountChangeDto, AccountUpdateDto> {

  private static final AccountUpdateDtoMapper INSTANCE = new AccountUpdateDtoMapper();


  private AccountUpdateDtoMapper() {
  }

  public static AccountUpdateDtoMapper getInstance() {
    return INSTANCE;
  }

  @Override
  public AccountUpdateDto mapFrom(AccountChangeDto changeDto) {
    if (Objects.isNull(changeDto)) {
      return null;
    }
    return new AccountUpdateDto(
        changeDto.phone(),
        changeDto.email(),
        changeDto.username()
    );
  }
}
