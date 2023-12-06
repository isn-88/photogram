package su.itpro.photogram.service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.Part;
import su.itpro.photogram.model.entity.Post;

public interface PostService {

  void createNewPost(String username, String description, Collection<Part> files);

  List<Post> findTopByAccountIdAndLimit(UUID accountId, int limit);

}
