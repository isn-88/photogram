package su.itpro.photogram.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.UUID;
import su.itpro.photogram.dao.CommentDao;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.mapper.CommentCreateMapper;
import su.itpro.photogram.mapper.CommentDtoMapper;
import su.itpro.photogram.model.dto.CommentCreateDto;
import su.itpro.photogram.model.dto.CommentDto;
import su.itpro.photogram.model.entity.Comment;
import su.itpro.photogram.service.CommentService;

public class CommentServiceImpl implements CommentService {

  private static final CommentService INSTANCE = new CommentServiceImpl();

  private final CommentCreateMapper commentCreateMapper;
  private final CommentDtoMapper commentDtoMapper;
  private final CommentDao commentDao;


  private CommentServiceImpl() {
    commentCreateMapper = CommentCreateMapper.getInstance();
    commentDtoMapper = CommentDtoMapper.getInstance();
    commentDao = DaoFactory.INSTANCE.getCommentDao();
  }

  public static CommentService getInstance() {
    return INSTANCE;
  }

  public List<CommentDto> findAllBy(UUID postId) {

    return commentDao.findAllByPostId(postId).stream()
        .map(commentDtoMapper::mapFrom)
        .collect(toList());
  }

  @Override
  public void saveComment(CommentCreateDto dto) {
    Comment comment = commentCreateMapper.mapFrom(dto);

    commentDao.save(comment);
  }

  @Override
  public void deleteComment(UUID id) {

    commentDao.delete(id);
  }

  @Override
  public void deleteAllCommentsByPostId(UUID postId) {

    commentDao.deleteByPostId(postId);
  }
}
