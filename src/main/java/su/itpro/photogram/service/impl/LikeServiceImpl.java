package su.itpro.photogram.service.impl;

import java.util.UUID;
import su.itpro.photogram.dao.LikeDao;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.model.Like;
import su.itpro.photogram.model.dto.LikeDto;
import su.itpro.photogram.service.LikeService;

public class LikeServiceImpl implements LikeService {

  private static final LikeService INSTANCE = new LikeServiceImpl();

  private final LikeDao likeDao;


  private LikeServiceImpl() {
    likeDao = DaoFactory.INSTANCE.getLikeDao();
  }

  public static LikeService getInstance() {
    return INSTANCE;
  }

  @Override
  public int getScore(UUID postId) {
    return likeDao.sumScore(postId);
  }

  @Override
  public void update(LikeDto dto) {
    Like like = new Like(dto.accountId(), dto.postId(), dto.score());
    if (likeDao.exists(like)) {
      likeDao.update(like);
    } else {
      likeDao.save(like);
    }
  }

  @Override
  public void deleteBy(UUID postId) {
    likeDao.deleteBy(postId);
  }


}
