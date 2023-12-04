package su.itpro.photogram.service;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.Part;
import su.itpro.photogram.model.dto.ImageBase64Dto;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.model.entity.Image;
import su.itpro.photogram.model.entity.Post;

public interface ImageService {

  List<Image> saveImages(Account account, Post post, Collection<Part> files);

  Map<UUID, ImageBase64Dto> loadPreviewImageFilesBy(List<Post> posts);
}
