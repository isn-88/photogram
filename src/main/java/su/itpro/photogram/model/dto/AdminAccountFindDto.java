package su.itpro.photogram.model.dto;

public record AdminAccountFindDto(String accountId,
                                  String username,
                                  String phone,
                                  String email,
                                  boolean onlyBlocked) {

}
