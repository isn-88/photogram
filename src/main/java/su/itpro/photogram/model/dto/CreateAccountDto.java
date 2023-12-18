package su.itpro.photogram.model.dto;

public record CreateAccountDto(String phone,
                               String email,
                               String username,
                               String password,
                               String fullName) {

}
