package su.itpro.photogram.model.dto;

import java.util.Collection;
import javax.servlet.http.Part;
import su.itpro.photogram.model.enums.PostStatus;

public record PostCreateDto(PostStatus status,
                            String description,
                            Collection<Part> parts) {

}
