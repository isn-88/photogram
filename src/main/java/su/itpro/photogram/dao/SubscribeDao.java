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
   * Возвращает 100 рекомендаций по количеству публикаций
   *
   * @return - список рекомендаций
   */
  List<AdviceDto> findTop100AdviceByPost();

  /**
   * Возвращает 100 рекомендаций по количеству подписок
   *
   * @return - список рекомендаций
   */
  List<AdviceDto> findTop100AdviceBySubscribes();

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
