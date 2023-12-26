package su.itpro.photogram.comparator;

import java.util.Comparator;
import su.itpro.photogram.model.dto.PostDto;

public class PostByTimeComparator implements Comparator<PostDto> {

  @Override
  public int compare(PostDto o1, PostDto o2) {
    return o2.createDate().compareTo(o1.createDate());
  }
}
