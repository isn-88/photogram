package su.itpro.photogram.mapper;

import java.time.Instant;
import su.itpro.photogram.model.dto.ComplaintCreateDto;
import su.itpro.photogram.model.entity.Complaint;
import su.itpro.photogram.model.enums.ComplainStatus;

public class ComplaintCreateMapper implements Mapper<ComplaintCreateDto, Complaint> {

  private static final ComplaintCreateMapper INSTANCE = new ComplaintCreateMapper();


  private ComplaintCreateMapper() {
  }

  public static ComplaintCreateMapper getInstance() {
    return INSTANCE;
  }

  @Override
  public Complaint mapFrom(ComplaintCreateDto dto) {
    return new Complaint(dto.accountId(),
                         null,
                         dto.postId(),
                         dto.message(),
                         ComplainStatus.OPEN,
                         Instant.now(),
                         null
    );
  }
}
