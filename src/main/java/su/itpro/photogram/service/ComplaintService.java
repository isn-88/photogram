package su.itpro.photogram.service;

import java.util.List;
import java.util.UUID;
import su.itpro.photogram.model.dto.ComplaintActionDto;
import su.itpro.photogram.model.dto.ComplaintCreateDto;
import su.itpro.photogram.model.dto.ComplaintDto;
import su.itpro.photogram.model.dto.ComplaintSearchDto;

public interface ComplaintService {

  boolean exists(UUID accountId, UUID postId);

  List<ComplaintDto> search(ComplaintSearchDto searchDto);

  void create(ComplaintCreateDto createDto);

  void action(ComplaintActionDto actionDto);

  void deleteByPost(UUID postId);
}
