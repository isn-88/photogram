package su.itpro.photogram.model.dto;

import java.util.Collection;
import javax.servlet.http.Part;

public record PostCreateDto(Boolean isActive,
                            String description,
                            Collection<Part> parts) {

}
