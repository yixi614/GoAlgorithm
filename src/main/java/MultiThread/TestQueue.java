package MultiThread;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.NoSuchElementException;

/**
 * Created by ztang16 on 8/6/2017.
 *
 */
public class TestQueue {

  public void start() {
    try {
      int queueCapacity = 100000;
      BoundedBlockingQueue<String> queue = null;
      BoundedBlockingQueue<String> queue1 =
              new BoundedBlockingQueueUsingObjectMonitor(queueCapacity);

      BoundedBlockingQueue<String> queue2 =
              new BoundedBlockingQueueUsingReentrantLock<String>(queueCapacity);

      queue = queue1;
      int workerCount = 10000;
      int operations = 10000;
      Thread[] producers = new Thread[workerCount];
      Thread[] consumers = new Thread[workerCount];
      for (int i = 0; i < workerCount; i++) {
        producers[i] = new Thread(new producer(queue, operations), "producer" + i);
        consumers[i] = new Thread(new consumer(queue,operations), "consumer" + i);
        consumers[i].start();
        System.out.println("consumer: " + i + " started!");
        producers[i].start();
        System.out.println("producer: " + i + " started!");
      }

      for (int i = 0; i < workerCount; i++) {
        producers[i].join();
        consumers[i].join();
      }
    } catch (InvalidArgumentException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  public static void main (String[] args) {
    TestQueue test = new TestQueue();
    Long timestamp = System.currentTimeMillis();
    test.start();
    Long timestamp2 = System.currentTimeMillis();
    System.out.println("Total spend:" + (timestamp2 - timestamp) + " ms");
  }

  public class producer implements Runnable {
    private BoundedBlockingQueue<String> queue;
    private int count = 0;
    private int operations = 0;
    public producer(BoundedBlockingQueue<String> queue, int count) {
      this.queue = queue;
      this.count = count;
    }
    public void run() {
      while (operations < count) {
        try {
          queue.add(Long.toString(Thread.currentThread().getId()));
          operations++;
//          System.out.println("producerTID:" + Long.toString(Thread.currentThread().getId()) +
//                  ", operations:" + operations +
//                  ", put its own id");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public class consumer implements Runnable {
    private BoundedBlockingQueue<String> queue;
    private int count = 0;
    private int operations = 0;
    public consumer(BoundedBlockingQueue<String> queue, int count) {
      this.queue = queue;
      this.count = count;
    }
    public void run() {
      while (operations < count) {
        try {
          String a = queue.remove();
          operations++;
//          System.out.println("consumerTID:" + Long.toString(Thread.currentThread().getId()) +
//                  ", operations:" + operations +
//                  ", remove and got element: " + a);
          //Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (NoSuchElementException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
