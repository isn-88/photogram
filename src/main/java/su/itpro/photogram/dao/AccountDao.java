package su.itpro.photogram.dao;

import java.util.Optional;
import java.util.UUID;
import su.itpro.photogram.model.entity.Account;

public interface AccountDao extends BaseDao<UUID, Account> {

  Optional<Account> findByUsername(String username);

  Optional<Account> findByEmail(String email);

  Optional<Account> findByPhone(String phone);

  void changePassword(Account account, String password);

}
