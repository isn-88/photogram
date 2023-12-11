package su.itpro.photogram.model.dto;

public record ChangePasswordDto(String username,
                                String currentPassword,
                                String newPassword,
                                String checkPassword) {

}
