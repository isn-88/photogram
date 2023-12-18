package su.itpro.photogram.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SaveImageThreadPool {

  private static final SaveImageThreadPool INSTANCE = new SaveImageThreadPool();

  private final ExecutorService pool;


  public SaveImageThreadPool() {
    pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
  }

  public static SaveImageThreadPool getInstance() {
    return INSTANCE;
  }

  public ExecutorService getPool() {
    return pool;
  }

  public void shutdown(long awaitSeconds) {
    pool.shutdown();
    try {
      pool.awaitTermination(awaitSeconds, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}
