package su.itpro.photogram.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.Part;
import su.itpro.photogram.model.dto.ImageAndBase64Dto;
import su.itpro.photogram.model.dto.ImageIdAndBase64Dto;
import su.itpro.photogram.model.dto.PostDto;


public interface ImageService {

  List<ImageAndBase64Dto> findImagesBy(UUID postId);

  void saveImages(UUID accountId, UUID postId, Collection<Part> files);

  Map<UUID, ImageIdAndBase64Dto> loadPreviewImageFilesBy(List<PostDto> posts);
}
