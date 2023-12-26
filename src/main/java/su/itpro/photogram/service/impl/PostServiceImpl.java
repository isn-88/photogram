package su.itpro.photogram.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.dao.ComplaintDao;
import su.itpro.photogram.dao.PostDao;
import su.itpro.photogram.exception.service.PostServiceException;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.mapper.PostDtoMapper;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.PostCreateDto;
import su.itpro.photogram.model.dto.PostDto;
import su.itpro.photogram.model.dto.PostUpdateDto;
import su.itpro.photogram.model.entity.Post;
import su.itpro.photogram.model.enums.PostStatus;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.CommentService;
import su.itpro.photogram.service.ImageService;
import su.itpro.photogram.service.LikeService;
import su.itpro.photogram.service.PostService;

public class PostServiceImpl implements PostService {

  private static final PostService INSTANCE = new PostServiceImpl();

  private final AccountService accountService;
  private final ImageService imageService;
  private final CommentService commentService;
  private final ComplaintDao complaintDao;
  private final LikeService likeService;
  private final PostDao postDao;
  private final PostDtoMapper postDtoMapper;


  private PostServiceImpl() {
    accountService = AccountServiceImpl.getInstance();
    imageService = ImageServiceImpl.getInstance();
    commentService = CommentServiceImpl.getInstance();
    complaintDao = DaoFactory.INSTANCE.getComplaintDao();
    likeService = LikeServiceImpl.getInstance();
    postDao = DaoFactory.INSTANCE.getPostDao();
    postDtoMapper = PostDtoMapper.getInstance();
  }

  public static PostService getInstance() {
    return INSTANCE;
  }

  @Override
  public void createNewPost(String username, PostCreateDto dto) {
    AccountDto accountDto = accountService.findByUsername(username);
    Post post = new Post(accountDto.id(), dto.status(), dto.description());
    postDao.save(post);
    imageService.saveImages(accountDto.id(), post.getId(), dto.parts());
  }

  @Override
  public List<PostDto> findTopPostIdByAccountIdAndLimit(UUID accountId,
                                                        boolean onlyPublic, int limit) {
    List<PostStatus> findStatuses = (onlyPublic)
                                ? List.of(PostStatus.PUBLIC)
                                : List.of(PostStatus.values());
    return postDao.findTopByAccountIdAndLimit(accountId, findStatuses, limit)
        .stream()
        .map(postDtoMapper::mapFrom)
        .collect(toList());
  }

  @Override
  public PostDto findById(UUID postId) {
    return postDtoMapper.mapFrom(postDao.findById(postId).orElseThrow(
        () -> new PostServiceException("Post with id [%s] not found".formatted(postId))
    ));
  }

  public int countPosts(UUID accountId) {
    return postDao.countPosts(accountId);
  }

  @Override
  public void update(PostUpdateDto dto) {
    Post post = postDao.findById(dto.id()).orElseThrow(
        () -> new PostServiceException("Post not found")
    );

    Optional.ofNullable(dto.description()).ifPresent(post::setDescription);
    Optional.ofNullable(dto.status()).ifPresent(post::setStatus);

    postDao.update(post);
  }

  @Override
  public void delete(UUID postId) {
    likeService.deleteBy(postId);
    commentService.deleteAllCommentsByPostId(postId);
    complaintDao.deleteByPost(postId);
    imageService.deleteImagesByPostId(postId);
    postDao.delete(postId);
  }

}
