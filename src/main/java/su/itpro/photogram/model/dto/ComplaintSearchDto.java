package su.itpro.photogram.model.dto;

import su.itpro.photogram.model.enums.ComplainStatus;

public record ComplaintSearchDto(ComplainStatus opened,
                                 ComplainStatus closed,
                                 ComplainStatus approved,
                                 ComplainStatus rejected) {

}
