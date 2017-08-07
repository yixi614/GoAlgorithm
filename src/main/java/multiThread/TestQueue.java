package multiThread;

import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Created by ztang16 on 8/6/2017.
 */
public class TestQueue {
  public void start() {
    try {
      int queueCapacity = 10000;
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
        producers[i] = new Thread(new producer(queue, operations));
        consumers[i] = new Thread(new consumer(queue,operations));
        producers[i].start();
        System.out.println("producer: " + i + " started!");
        Thread.sleep(1);
        consumers[i].start();
        System.out.println("consumer: " + i + " started!");
      }

    } catch (InvalidArgumentException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  public static void main (String[] args) {
    //test the bounded blocking queue implemented using object monitor(sychronized, wait and notifyAll)
    TestQueue test = new TestQueue();
    test.start();
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
          System.out.println("producerTID:" + Long.toString(Thread.currentThread().getId()) +
                  ", operations:" + operations +
                  ", put its own id");
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
          System.out.println("consumerTID:" + Long.toString(Thread.currentThread().getId()) +
                  ", operations:" + operations +
                  ", remove and got element: " + a);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
