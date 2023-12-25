package su.itpro.photogram.model.dto;

import java.util.List;
import su.itpro.photogram.model.enums.ComplainStatus;

public record ComplainFindDto(List<ComplainStatus> statuses) {

}
