package su.itpro.photogram.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.Part;
import su.itpro.photogram.dao.PostDao;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.model.entity.Post;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.ImageService;
import su.itpro.photogram.service.PostService;

public class PostServiceImpl implements PostService {

  private static final PostService INSTANCE = new PostServiceImpl();

  private final AccountService accountService;
  private final ImageService imageService;
  private final PostDao postDao;


  private PostServiceImpl() {
    accountService = AccountServiceImpl.getInstance();
    imageService = ImageServiceImpl.getInstance();
    postDao = DaoFactory.INSTANCE.getPostDao();
  }

  public static PostService getInstance() {
    return INSTANCE;
  }

  public void createNewPost(String username, String description, Collection<Part> files) {
    Account account = accountService.findByUsername(username);
    Post post = new Post(account.getId(), description);
    postDao.save(post);
    imageService.saveImages(account, post, files);
  }

  public List<Post> findTopByAccountIdAndLimit(UUID accountId, int limit) {
    return postDao.findTopByAccountIdAndLimit(accountId, limit);
  }

}
