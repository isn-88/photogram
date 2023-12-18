package su.itpro.photogram.model.dto;

import su.itpro.photogram.model.enums.Gender;

public record ProfileCreateDto(String fullName, Gender gender) {

}
