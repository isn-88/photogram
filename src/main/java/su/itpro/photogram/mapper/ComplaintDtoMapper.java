package su.itpro.photogram.mapper;

import su.itpro.photogram.model.dto.ComplaintDto;
import su.itpro.photogram.model.entity.Complaint;

public class ComplaintDtoMapper implements Mapper<Complaint, ComplaintDto> {

  private static final ComplaintDtoMapper INSTANCE = new ComplaintDtoMapper();


  private ComplaintDtoMapper() {
  }

  public static ComplaintDtoMapper getInstance() {
    return INSTANCE;
  }

  @Override
  public ComplaintDto mapFrom(Complaint complaint) {
    return new ComplaintDto(
        complaint.getAccountId(),
        complaint.getUsername(),
        complaint.getPostId(),
        complaint.getMessage(),
        complaint.getStatus(),
        complaint.getCreateTime(),
        complaint.getCloseTime()
    );
  }
}
