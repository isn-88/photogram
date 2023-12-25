package su.itpro.photogram.mapper;

import java.util.Objects;
import java.util.stream.Stream;
import su.itpro.photogram.model.dto.ComplainFindDto;
import su.itpro.photogram.model.dto.ComplaintSearchDto;

public class ComplaintFindDtoMapper implements Mapper<ComplaintSearchDto, ComplainFindDto> {

  private static final ComplaintFindDtoMapper INSTANCE = new ComplaintFindDtoMapper();


  private ComplaintFindDtoMapper() {
  }

  public static ComplaintFindDtoMapper getInstance() {
    return INSTANCE;
  }

  @Override
  public ComplainFindDto mapFrom(ComplaintSearchDto searchDto) {
    return new ComplainFindDto(
        Stream.of(
                searchDto.opened(),
                searchDto.closed(),
                searchDto.approved(),
                searchDto.rejected()
            )
            .filter(Objects::nonNull)
            .toList()
    );
  }
}
