package MultiThread;

import java.util.NoSuchElementException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by ztang16 on 8/6/2017.
 *
 */
public class TestQueue2 {

  public void start() {
    try {
      int queueCapacity = 100000;
      LinkedBlockingQueue queue = new LinkedBlockingQueue(queueCapacity);

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
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  public static void main (String[] args) {
    TestQueue2 test = new TestQueue2();
    Long timestamp = System.currentTimeMillis();
    test.start();
    Long timestamp2 = System.currentTimeMillis();
    System.out.println("Total spend:" + (timestamp2 - timestamp) + " ms");
  }

  public class producer implements Runnable {
    private LinkedBlockingQueue<String> queue;
    private int count = 0;
    private int operations = 0;
    public producer(LinkedBlockingQueue<String> queue, int count) {
      this.queue = queue;
      this.count = count;
    }
    public void run() {
      while (operations < count) {
        try {
          queue.put(Long.toString(Thread.currentThread().getId()));
          operations++;
//          System.out.println("producerTID:" + Long.toString(Thread.currentThread().getId()) +
//                  ", operations:" + operations +
//                  ", put its own id");
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  public class consumer implements Runnable {
    private LinkedBlockingQueue<String> queue;
    private int count = 0;
    private int operations = 0;
    public consumer(LinkedBlockingQueue<String> queue, int count) {
      this.queue = queue;
      this.count = count;
    }
    public void run() {
      while (operations < count) {
        try {
          String a = queue.take();
          operations++;
//          System.out.println("consumerTID:" + Long.toString(Thread.currentThread().getId()) +
//                  ", operations:" + operations +
//                  ", remove and got element: " + a);
          //Thread.sleep(1);
        } catch (NoSuchElementException e) {
          e.printStackTrace();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
