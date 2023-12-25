package su.itpro.photogram.dao;

import java.util.List;
import java.util.UUID;
import su.itpro.photogram.model.dto.ComplainFindDto;
import su.itpro.photogram.model.entity.Complaint;

public interface ComplaintDao {

  boolean exist(UUID accountId, UUID postId);

  List<Complaint> findAllBy(ComplainFindDto complainFindDto);

  void save(Complaint complaint);

  void update(Complaint complaint);

  void deleteByPost(UUID postId);
}
