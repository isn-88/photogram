package su.itpro.photogram.service;

import java.util.UUID;
import su.itpro.photogram.dao.ContactDao;
import su.itpro.photogram.dao.impl.ContactDaoImpl;
import su.itpro.photogram.entity.Contact;
import su.itpro.photogram.exception.ContactServiceException;

public class ContactService {

  private static final ContactService INSTANCE = new ContactService();

  private final ContactDao contactDao = ContactDaoImpl.getInstance();

  private ContactService() {
  }

  public static ContactService getInstance() {
    return INSTANCE;
  }

  public Contact findById(UUID id) {
    return contactDao.findById(id)
        .orElseThrow(() -> new ContactServiceException("Contact not found!"));
  }

  public void update(Contact contact) {
    contactDao.update(contact);
  }
}
