package su.itpro.photogram.model.dto;

import java.util.UUID;

public record ChangePasswordDto(UUID accountId,
                                String currentPassword,
                                String newPassword,
                                String checkPassword) {

}
