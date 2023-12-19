package su.itpro.photogram.mapper;

import su.itpro.photogram.model.dto.CommentCreateDto;
import su.itpro.photogram.model.entity.Comment;

public class CommentCreateMapper implements Mapper<CommentCreateDto, Comment> {

  private static final CommentCreateMapper INSTANCE = new CommentCreateMapper();


  private CommentCreateMapper() {
  }

  public static CommentCreateMapper getInstance() {
    return INSTANCE;
  }

  public Comment mapFrom(CommentCreateDto dto) {
    return new Comment(
        dto.accountId(),
        dto.postId(),
        dto.message()
    );
  }

}
