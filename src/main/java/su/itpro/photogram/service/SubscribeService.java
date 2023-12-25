package su.itpro.photogram.service;

import java.util.List;
import java.util.UUID;
import su.itpro.photogram.model.dto.AdviceDto;
import su.itpro.photogram.model.dto.PostDto;
import su.itpro.photogram.model.dto.SubscribeDto;

/**
 *  Интерфейс по работе с подписками и подписчиками
 */
public interface SubscribeService {

  /**
   * Формирует рекомендации для последующей подписки
   *
   * @param accountId - для какого аккаунта сформировать рекомендации
   * @param limit - ограничение по количеству рекомендаций
   * @return - список пользователей
   */
  List<AdviceDto> getAdvice(UUID accountId, int limit);

  /**
   * Предоставляет список последних публикаций
   * по указанному списку идентификаторов подписок
   *
   * @param subscribeIds - список идентификаторов подписок
   * @param limit - ограничение по количеству публикаций
   * @return - список последних публикаций
   */
  List<PostDto> getSubscribePost(List<UUID> subscribeIds, int limit);

  /**
   * Производит поиск подписок пользователя
   *
   * @param accountId - идентификатор пользователя для которого формируется список подписок
   * @param limit - ограничение результирующего списка
   * @return - список подписок
   */
  List<AdviceDto> findSubscribe(UUID accountId, int limit);

  /***
   * Производит поиск всех подписок пользователя
   *
   * @param accountId - идентификатор пользователя для которого формируется список подписок
   * @return - список всех подписок
   */
  List<UUID> findAllSubscribe(UUID accountId);

  /**
   * Производит поиск подписчиков пользователя
   *
   * @param accountId - идентификатор пользователя для которого формируется список подписчиков
   * @param limit - ограничение результирующего списка
   * @return - список подписчиков
   */
  List<AdviceDto> findSubscribers(UUID accountId, int limit);

  /**
   * Производит подсчёт всех подписок
   *
   * @param accountId - идентификатор пользователя
   * @return - общее количество подписок
   */
  int subscribeCount(UUID accountId);

  /**
   * Производит подсчёт всех подписчиков
   *
   * @param accountId - идентификатор пользователя
   * @return - общее количество подписчиков
   */
  int subscribersCount(UUID accountId);

  /**
   * Проверяет возможность подписаться
   *
   * @param accountId - идентификатор аккаунта
   * @param subscribeId - идентификатор аккаунта для подписки
   * @return true если можно подписаться
   */
  boolean readyToSubscribe(UUID accountId, UUID subscribeId);

  /**
   * Осуществляет подписку
   *
   * @param dto - данные для подписки
   */
  void subscribe(SubscribeDto dto);

  /**
   * Осуществляет отписку
   *
   * @param dto - данные для отписки
   */
  void unsubscribe(SubscribeDto dto);
}

