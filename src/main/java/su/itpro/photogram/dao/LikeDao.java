package su.itpro.photogram.dao;

import java.util.UUID;
import su.itpro.photogram.model.Like;

public interface LikeDao {

  int sumScore(UUID postId);

  boolean exists(Like like);

  void save(Like like);

  void update(Like like);

  void deleteBy(UUID postId);
}
