package su.itpro.photogram.model.dto;

public record AccountChangeDto(String phone,
                               String email,
                               String username,
                               String password,
                               String fullName) {

}
