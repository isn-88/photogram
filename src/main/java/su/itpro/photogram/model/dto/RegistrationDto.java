package su.itpro.photogram.model.dto;

public record RegistrationDto(String phone,
                              String email,
                              String username,
                              String password,
                              String fullName) {

}
