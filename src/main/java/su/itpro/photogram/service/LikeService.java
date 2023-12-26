package su.itpro.photogram.service;

import java.util.UUID;
import su.itpro.photogram.model.dto.LikeDto;

public interface LikeService {

  int getScore(UUID id, UUID postId);

  int getTotalScore(UUID postId);

  void update(LikeDto dto);

  void deleteBy(UUID postId);
}
