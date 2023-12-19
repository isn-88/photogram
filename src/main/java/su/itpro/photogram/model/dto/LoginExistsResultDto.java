package su.itpro.photogram.model.dto;

public record LoginExistsResultDto(boolean isExistsPhone,
                                   boolean isExistsEmail,
                                   boolean isExistsUsername) {

}
