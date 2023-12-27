package su.itpro.photogram.dao;

import java.util.List;
import java.util.UUID;
import su.itpro.photogram.model.dto.AdviceDto;
import su.itpro.photogram.model.dto.SubscribeUserDto;
import su.itpro.photogram.model.entity.Subscribe;

/**
 * Хранение данных о подписках
 */
public interface SubscribeDao {

  /**
   * Возвращает рекомендации сформированные по количеству публикаций
   *
   * @param limit - ограничение результирующего списка
   * @return - список рекомендаций
   */
  List<AdviceDto> findTopAdviceByPost(int limit);

  /**
   * Возвращает рекомендации сформированные по количеству подписок
   *
   * @param limit - ограничение результирующего списка
   * @return - список рекомендаций
   */
  List<AdviceDto> findTopAdviceBySubscribes(int limit);

  /**
   * Поиск всех подписок для указанного аккаунта
   *
   * @param accountId - идентификатор аккаунта
   * @return - список всех подписок
   */
  List<SubscribeUserDto> findAllSubscribe(UUID accountId);

  /**
   * Поиск всех подписчиков для указанного аккаунта
   *
   * @param accountId - идентификатор аккаунта
   * @return - список всех подписчиков
   */
  List<SubscribeUserDto> findAllSubscribers(UUID accountId);

  /**
   * Посчитывает все подписки
   *
   * @param accountId - идентификатор аккаунта
   * @return - количество подписок
   */
  int subscribeCount(UUID accountId);

  /**
   * Посчитывает всех подписчиков
   *
   * @param accountId - идентификатор аккаунта
   * @return - количество подписчиков
   */
  int subscribersCount(UUID accountId);

  /**
   * Проверяет возможность подписаться
   *
   * @param accountId - идентификатор аккаунта
   * @param subscribeId - идентификатор подписки
   * @return - true если можно подписаться
   */
  boolean readyToSubscribe(UUID accountId, UUID subscribeId);

  /**
   * Производит подписку на публикации пользователя
   *
   * @param dto - данные для подписки
   */
  void subscribe(Subscribe dto);

  /**
   * Производит отписку от публикаций пользователя
   *
   * @param dto - данные для отписки
   */
  void unsubscribe(Subscribe dto);
}
