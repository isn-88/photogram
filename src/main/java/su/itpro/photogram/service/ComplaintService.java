package su.itpro.photogram.service;

import java.util.List;
import java.util.UUID;
import su.itpro.photogram.model.dto.ComplaintActionDto;
import su.itpro.photogram.model.dto.ComplaintCreateDto;
import su.itpro.photogram.model.dto.ComplaintDto;
import su.itpro.photogram.model.dto.ComplaintSearchDto;

/**
 * Сервис для работы с обращениями пользователей
 */
public interface ComplaintService {

  /**
   * Проверяет наличие обращения
   *
   * @param accountId - идентификатор аккаунта
   * @param postId - идентификатор публикации
   * @return - true если у пользователя есть обращение
   */
  boolean exists(UUID accountId, UUID postId);

  /**
   * Поиск обращений с фильтрами по статусу
   *
   * @param searchDto - фильтры
   * @return - список обращений
   */
  List<ComplaintDto> search(ComplaintSearchDto searchDto);

  /**
   * Создаёт обращение
   *
   * @param createDto - данные для создания обращения
   */
  void create(ComplaintCreateDto createDto);

  /**
   * Обработка обращения
   *
   * @param actionDto - данные для обработки
   */
  void action(ComplaintActionDto actionDto);

}
