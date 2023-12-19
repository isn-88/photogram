package su.itpro.photogram.mapper;

import su.itpro.photogram.model.dto.CommentDto;
import su.itpro.photogram.model.entity.Comment;
import su.itpro.photogram.util.CommentUtil;

public class CommentDtoMapper implements Mapper<Comment, CommentDto> {

  private static final CommentDtoMapper INSTANCE = new CommentDtoMapper();


  private CommentDtoMapper() {
  }

  public static CommentDtoMapper getInstance() {
    return INSTANCE;
  }

  @Override
  public CommentDto mapFrom(Comment comment) {
    return new CommentDto(
        comment.getId(),
        comment.getAccountId(),
        comment.getPostId(),
        CommentUtil.getCommentElapsedTime(
            comment.getCreateTime(),
            comment.getChangeTime(),
            comment.getDeleted()
        ),
        comment.getDeleted(),
        comment.getMessage()
    );
  }

}
