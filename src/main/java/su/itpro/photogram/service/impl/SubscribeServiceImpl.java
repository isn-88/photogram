package su.itpro.photogram.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import su.itpro.photogram.comparator.PostByTimeComparator;
import su.itpro.photogram.dao.SubscribeDao;
import su.itpro.photogram.factory.DaoFactory;
import su.itpro.photogram.model.dto.AdviceDto;
import su.itpro.photogram.model.dto.PostDto;
import su.itpro.photogram.model.dto.SubscribeDto;
import su.itpro.photogram.model.dto.SubscribeUserDto;
import su.itpro.photogram.model.entity.Subscribe;
import su.itpro.photogram.service.PostService;
import su.itpro.photogram.service.SubscribeService;

public class SubscribeServiceImpl implements SubscribeService {

  private static final int ADVICE_QUERY_LIMIT = 100;

  private static final SubscribeService INSTANCE = new SubscribeServiceImpl();

  private final SubscribeDao subscribeDao;
  private final PostService postService;


  private SubscribeServiceImpl() {
    subscribeDao = DaoFactory.INSTANCE.getSubscribeDao();
    postService = PostServiceImpl.getInstance();
  }

  public static SubscribeService getInstance() {
    return INSTANCE;
  }

  @Override
  public List<AdviceDto> getAdvice(UUID accountId, int limit) {
    List<AdviceDto> byPost = subscribeDao.findTopAdviceByPost(ADVICE_QUERY_LIMIT);
    List<AdviceDto> bySubscribes = subscribeDao.findTopAdviceBySubscribes(ADVICE_QUERY_LIMIT);
    Set<AdviceDto> result = new HashSet<>(byPost);
    result.addAll(bySubscribes);

    List<AdviceDto> allSubscribers = subscribeDao.findAllSubscribe(accountId).stream()
        .map(s -> new AdviceDto(s.subscribeId(), s.subscribeUsername()))
        .toList();
    allSubscribers.forEach(result::remove);
    return result.stream()
        .filter(advice -> !advice.id().equals(accountId))
        .limit(limit)
        .toList();
  }

  @Override
  public List<PostDto> getSubscribePost(List<UUID> subscribeIds, int limit) {
    Set<PostDto> allSubscribePostDtoSet = new TreeSet<>(new PostByTimeComparator());
    subscribeIds.forEach(id -> allSubscribePostDtoSet.addAll(
        postService.findTopPostIdByAccountIdAndLimit(id, true, limit)));
    return allSubscribePostDtoSet.stream()
        .limit(limit)
        .toList();
  }

  @Override
  public List<AdviceDto> findSubscribe(UUID accountId, int limit) {
    return subscribeDao.findAllSubscribe(accountId).stream()
        .map(s -> new AdviceDto(s.subscribeId(), s.subscribeUsername()))
        .limit(limit)
        .toList();
  }

  @Override
  public List<UUID> findAllSubscribe(UUID accountId) {
    return subscribeDao.findAllSubscribe(accountId).stream()
        .map(SubscribeUserDto::subscribeId)
        .toList();
  }

  @Override
  public List<AdviceDto> findSubscribers(UUID accountId, int limit) {
    return subscribeDao.findAllSubscribers(accountId).stream()
        .map(s -> new AdviceDto(s.accountId(), s.subscribeUsername()))
        .limit(limit)
        .toList();
  }

  @Override
  public int subscribeCount(UUID accountId) {
    return subscribeDao.subscribeCount(accountId);
  }

  @Override
  public int subscribersCount(UUID accountId) {
    return subscribeDao.subscribersCount(accountId);
  }

  @Override
  public boolean readyToSubscribe(UUID accountId, UUID subscribeId) {
    return subscribeDao.readyToSubscribe(accountId, subscribeId);
  }

  @Override
  public void subscribe(SubscribeDto dto) {
    Subscribe subscribe = new Subscribe(dto.accountId(), dto.subscribeId());
    subscribeDao.subscribe(subscribe);
  }

  @Override
  public void unsubscribe(SubscribeDto dto) {
    Subscribe subscribe = new Subscribe(dto.accountId(), dto.subscribeId());
    subscribeDao.unsubscribe(subscribe);
  }
}
