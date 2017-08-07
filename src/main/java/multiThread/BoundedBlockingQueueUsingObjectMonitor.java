package multiThread;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ztang16 on 8/6/2017.
 */
public class BoundedBlockingQueueUsingObjectMonitor<E> implements BoundedBlockingQueue{
  private final Queue<E> queue = new LinkedList<E>();
  private final int capacity;
  private final AtomicInteger count = new AtomicInteger(0);

  public BoundedBlockingQueueUsingObjectMonitor(int capacity) throws InvalidArgumentException {
    if (capacity <= 0)  throw new InvalidArgumentException(
            new String[]{"The capacity of the queue must be > 0."});
    this.capacity = capacity;
  }

  public int size() {
    return count.get();
  }

  public void add(Object o) throws InterruptedException {
    _add(o);
  }

  public Object remove() throws InterruptedException {
    return _remove();
  }

  public synchronized void _add(Object e) throws RuntimeException, InterruptedException {
    if (e == null) throw new NullPointerException("Null element is not allowed.");

    int oldCount = -1;
    while (count.get() == capacity) wait();

    queue.add((E) e);
    oldCount = count.getAndIncrement();
    //only notify all when needed. can boost performance
    if (oldCount == 0) {
      notifyAll(); // notify other waiting threads (could be producers or consumers)
    }
  }

  public synchronized E _remove() throws NoSuchElementException, InterruptedException {
    E e;

    int oldCount = -1;
    while (count.get() == 0) wait();

    e = queue.remove();
    oldCount = count.getAndDecrement();
    if (oldCount == this.capacity) {
      notifyAll(); // notify other waiting threads (could be producers or consumers)
    }
    return e;
  }

  /* Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty. */
  public E peek() {
    if (count.get() == 0) return null;
    synchronized(this) {
      return queue.peek();
    }
  }
}
