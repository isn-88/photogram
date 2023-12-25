package su.itpro.photogram.mapper;

import su.itpro.photogram.model.dto.ComplaintActionDto;
import su.itpro.photogram.model.entity.Complaint;

public class ComplaintMapper implements Mapper<ComplaintActionDto, Complaint> {

  private static final ComplaintMapper INSTANCE = new ComplaintMapper();


  private ComplaintMapper() {
  }

  public static ComplaintMapper getInstance() {
    return INSTANCE;
  }

  @Override
  public Complaint mapFrom(ComplaintActionDto dto) {
    return new Complaint(dto.accountId(),
                         null,
                         dto.postId(),
                         null,
                         dto.status(),
                         null,
                         null);
  }
}
