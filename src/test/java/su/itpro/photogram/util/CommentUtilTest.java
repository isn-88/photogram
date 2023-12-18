package su.itpro.photogram.util;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;

class CommentUtilTest {

  @Test
  void getCommentElapsedTime_create30SecondsAgo() {
    Instant now = Instant.now();
    Instant createTime = now.minusSeconds(30);

    String info = CommentUtil.getCommentElapsedTime(createTime, null, null);

    assertNotNull(info);
    assertThat(info).isEqualTo("Опубликовано: сейчас");
  }

  @Test
  void getCommentElapsedTime_create1minutesAgo() {
    Instant now = Instant.now();
    Instant createTime = now.minus(1L, ChronoUnit.MINUTES);

    String info = CommentUtil.getCommentElapsedTime(createTime, null, null);

    assertNotNull(info);
    assertThat(info).isEqualTo("Опубликовано: 1 м. назад");
  }

  @Test
  void getCommentElapsedTime_create1hoursAgo() {
    Instant now = Instant.now();
    Instant createTime = now.minus(1L, ChronoUnit.HOURS);

    String info = CommentUtil.getCommentElapsedTime(createTime, null, null);

    assertNotNull(info);
    assertThat(info).isEqualTo("Опубликовано: 1 ч. назад");
  }

  @Test
  void getCommentElapsedTime_create1daysAgo() {
    Instant now = Instant.now();
    Instant createTime = now.minus(1L, ChronoUnit.DAYS);

    String info = CommentUtil.getCommentElapsedTime(createTime, null, null);

    assertNotNull(info);
    assertThat(info).isEqualTo("Опубликовано: 1 д. назад");
  }

  @Test
  void getCommentElapsedTime_change30SecondsAgo() {
    Instant now = Instant.now();
    Instant createTime = now.minus(2L, ChronoUnit.DAYS);
    Instant changeTime = now.minusSeconds(30);

    String info = CommentUtil.getCommentElapsedTime(createTime, changeTime, null);

    assertNotNull(info);
    assertThat(info).isEqualTo("Изменено: сейчас");
  }

  @Test
  void getCommentElapsedTime_change1minutesAgo() {
    Instant now = Instant.now();
    Instant createTime = now.minus(2L, ChronoUnit.DAYS);
    Instant changeTime = now.minus(1L, ChronoUnit.MINUTES);

    String info = CommentUtil.getCommentElapsedTime(createTime, changeTime, null);

    assertNotNull(info);
    assertThat(info).isEqualTo("Изменено: 1 м. назад");
  }

  @Test
  void getCommentElapsedTime_change1hoursAgo() {
    Instant now = Instant.now();
    Instant createTime = now.minus(2L, ChronoUnit.DAYS);
    Instant changeTime = now.minus(1L, ChronoUnit.HOURS);

    String info = CommentUtil.getCommentElapsedTime(createTime, changeTime, null);

    assertNotNull(info);
    assertThat(info).isEqualTo("Изменено: 1 ч. назад");
  }

  @Test
  void getCommentElapsedTime_change1daysAgo() {
    Instant now = Instant.now();
    Instant createTime = now.minus(2L, ChronoUnit.DAYS);
    Instant changeTime = now.minus(1L, ChronoUnit.DAYS);

    String info = CommentUtil.getCommentElapsedTime(createTime, changeTime, null);

    assertNotNull(info);
    assertThat(info).isEqualTo("Изменено: 1 д. назад");
  }

  @Test
  void getCommentElapsedTime_delete30SecondsAgo() {
    Instant now = Instant.now();
    Instant createTime = now.minus(2L, ChronoUnit.DAYS);
    Instant changeTime = now.minusSeconds(30L);

    String info = CommentUtil.getCommentElapsedTime(createTime, changeTime, true);

    assertNotNull(info);
    assertThat(info).isEqualTo("Удалено: сейчас");
  }

  @Test
  void getCommentElapsedTime_delete1minutesAgo() {
    Instant now = Instant.now();
    Instant createTime = now.minus(2L, ChronoUnit.DAYS);
    Instant changeTime = now.minus(1L, ChronoUnit.MINUTES);

    String info = CommentUtil.getCommentElapsedTime(createTime, changeTime, true);

    assertNotNull(info);
    assertThat(info).isEqualTo("Удалено: 1 м. назад");
  }

  @Test
  void getCommentElapsedTime_delete1hoursAgo() {
    Instant now = Instant.now();
    Instant createTime = now.minus(2L, ChronoUnit.DAYS);
    Instant changeTime = now.minus(1L, ChronoUnit.HOURS);

    String info = CommentUtil.getCommentElapsedTime(createTime, changeTime, true);

    assertNotNull(info);
    assertThat(info).isEqualTo("Удалено: 1 ч. назад");
  }

  @Test
  void getCommentElapsedTime_delete1daysAgo() {
    Instant now = Instant.now();
    Instant createTime = now.minus(2L, ChronoUnit.DAYS);
    Instant changeTime = now.minus(1L, ChronoUnit.DAYS);

    String info = CommentUtil.getCommentElapsedTime(createTime, changeTime, true);

    assertNotNull(info);
    assertThat(info).isEqualTo("Удалено: 1 д. назад");
  }

}