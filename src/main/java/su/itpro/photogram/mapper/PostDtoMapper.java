package su.itpro.photogram.mapper;

import java.util.Objects;
import su.itpro.photogram.model.dto.PostDto;
import su.itpro.photogram.model.entity.Post;

public final class PostDtoMapper implements Mapper<Post, PostDto> {

  private static final PostDtoMapper INSTANCE = new PostDtoMapper();


  private PostDtoMapper() {
  }

  public static PostDtoMapper getInstance() {
    return INSTANCE;
  }

  @Override
  public PostDto mapFrom(Post post) {
    if (Objects.isNull(post)) {
      return null;
    }
    return new PostDto(
        post.getId(),
        post.getAccountId(),
        post.getActive(),
        post.getDescription(),
        post.getCreateDate()
    );
  }

}
