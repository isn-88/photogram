package su.itpro.photogram.mapper;


import java.util.Objects;
import su.itpro.photogram.model.dto.PostDto;
import su.itpro.photogram.model.entity.Post;

public final class PostMapper {

  private static final PostMapper INSTANCE = new PostMapper();


  private PostMapper() {
  }

  public static PostMapper getInstance() {
    return INSTANCE;
  }

  public PostDto mapToPostDto(Post post) {
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
