package su.itpro.photogram.model.dto;

import java.util.List;
import su.itpro.photogram.model.enums.Role;
import su.itpro.photogram.model.enums.Status;

public record AccountFindDto(String accountId,
                             String username,
                             String phone,
                             String email,
                             List<Status> statuses,
                             List<Role> roles) {

}
