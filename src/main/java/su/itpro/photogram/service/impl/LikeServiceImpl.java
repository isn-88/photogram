package su.itpro.photogram.service.impl;

import java.util.UUID;
import su.itpro.photogram.dao.LikeDao;
import su.itpro.photogram.exception.service.LikeServiceException;
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
  public int getScore(UUID id, UUID postId) {
    return likeDao.getScore(id, postId);
  }

  @Override
  public int getTotalScore(UUID postId) {
    return likeDao.sumScore(postId);
  }

  @Override
  public void update(LikeDto dto) {
    Like like = new Like(dto.accountId(), dto.postId(), dto.score());
    if (likeDao.exists(like)) {
      if (!likeDao.update(like)) {
        throw new LikeServiceException("Like [accountId=%s, postId=%s] was not updated".formatted(
            dto.accountId(),
            dto.postId()
        ));
      }
    } else {
      likeDao.save(like);
    }
  }

  @Override
  public void deleteAllByPostId(UUID postId) {
    likeDao.deleteAllByPostId(postId);
  }

}
