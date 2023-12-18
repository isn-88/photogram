package su.itpro.photogram.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.itpro.photogram.dao.AccountDao;
import su.itpro.photogram.exception.dao.DaoException;
import su.itpro.photogram.exception.service.LoginServiceException;
import su.itpro.photogram.exception.validation.ValidationException;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.mapper.AccountDtoMapper;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.model.dto.LoginDto;
import su.itpro.photogram.model.entity.Account;
import su.itpro.photogram.service.LoginService;
import su.itpro.photogram.validator.LoginValidator;
import su.itpro.photogram.validator.ValidationResult;

public class LoginServiceImpl implements LoginService {

  private static final LoginService INSTANCE = new LoginServiceImpl();

  private final Logger log;
  private final AccountDao accountDao;
  private final AccountDtoMapper accountDtoMapper;
  private final LoginValidator loginValidator;


  private LoginServiceImpl() {
    log = LoggerFactory.getLogger(LoginServiceImpl.class);
    accountDao = DaoFactory.INSTANCE.getAccountDao();
    accountDtoMapper = AccountDtoMapper.getInstance();
    loginValidator = LoginValidator.getInstance();
  }

  public static LoginService getInstance() {
    return INSTANCE;
  }

  @Override
  public AccountDto login(LoginDto dto) {

    ValidationResult validationResult = loginValidator.validate(dto);
    if (validationResult.hasErrors()) {
      throw new ValidationException(validationResult.getErrors());
    }

    Optional<Account> accountOpt;
    String login = dto.login();
    try {
      if (login.contains("@")) {
        accountOpt = accountDao.findByEmail(login);
      } else if (login.startsWith("+")) {
        accountOpt = accountDao.findByPhone(login.substring(1));
      } else if (Character.isDigit(login.charAt(0))) {
        accountOpt = accountDao.findByPhone(login);
      } else {
        accountOpt = accountDao.findByUsername(login);
      }
    } catch (DaoException e) {
      log.error("Error load Account {}", e.getMessage());
      throw new LoginServiceException(e.getMessage());
    }

    validationResult = loginValidator.validateLogin(accountOpt, dto);
    if (validationResult.hasErrors()) {
      throw new ValidationException(validationResult.getErrors());
    }

    return accountDtoMapper.mapFrom(accountOpt.get());
  }
}
