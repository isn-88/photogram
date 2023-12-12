package su.itpro.photogram.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.dao.PostDao;
import su.itpro.photogram.exception.service.PostServiceException;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.mapper.PostMapper;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.PostCreateDto;
import su.itpro.photogram.model.dto.PostDto;
import su.itpro.photogram.model.dto.PostUpdateDto;
import su.itpro.photogram.model.entity.Post;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.ImageService;
import su.itpro.photogram.service.PostService;

public class PostServiceImpl implements PostService {

  private static final PostService INSTANCE = new PostServiceImpl();

  private final AccountService accountService;
  private final ImageService imageService;
  private final PostDao postDao;
  private final PostMapper postMapper;


  private PostServiceImpl() {
    accountService = AccountServiceImpl.getInstance();
    imageService = ImageServiceImpl.getInstance();
    postDao = DaoFactory.INSTANCE.getPostDao();
    postMapper = PostMapper.getInstance();
  }

  public static PostService getInstance() {
    return INSTANCE;
  }

  @Override
  public void createNewPost(String username, PostCreateDto dto) {
    AccountDto accountDto = accountService.findByUsername(username);
    Post post = new Post(accountDto.id(), dto.isActive(), dto.description());
    postDao.save(post);
    imageService.saveImages(accountDto.id(), post.getId(), dto.parts());
  }

  @Override
  public List<PostDto> findTopPostIdByAccountIdAndLimit(UUID accountId,
                                                        boolean onlyIsActive, int limit) {
    return postDao.findTopByAccountIdAndLimit(accountId, onlyIsActive, limit)
        .stream()
        .map(postMapper::mapToPostDto)
        .collect(toList());
  }

  @Override
  public PostDto findById(UUID postId) {
    return postMapper.mapToPostDto(postDao.findById(postId).orElseThrow(
        () -> new PostServiceException("Post with id [%s] not found".formatted(postId))
    ));
  }

  @Override
  public void update(PostUpdateDto dto) {
    Post post = postDao.findById(dto.id()).orElseThrow(
        () -> new PostServiceException("Post not found")
    );

    Optional.ofNullable(dto.description()).ifPresent(post::setDescription);
    Optional.ofNullable(dto.isActive()).ifPresent(post::setActive);

    postDao.update(post);
  }

}
