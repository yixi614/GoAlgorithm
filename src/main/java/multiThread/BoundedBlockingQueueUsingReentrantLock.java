package multiThread;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ztang16 on 8/6/2017.
 * TODO: this implementation has bug when remove. try to fix this
 */
public class BoundedBlockingQueueUsingReentrantLock<E> implements BoundedBlockingQueue{
  private final Queue<E> queue = new LinkedList<E>();
  private final int capacity;
  private final AtomicInteger count = new AtomicInteger(0);

  private final ReentrantLock putLock = new ReentrantLock();
  private final ReentrantLock takeLock = new ReentrantLock();

  private final Condition notFull = putLock.newCondition();
  private final Condition notEmpty = takeLock.newCondition();

  public BoundedBlockingQueueUsingReentrantLock(int capacity) throws InvalidArgumentException {
    if (capacity <= 0)  throw new InvalidArgumentException(
            new String[] {"The capacity of the queue must be > 0."});
    this.capacity = capacity;
  }

  public int size() {
    return count.get();
  }

  public void add(Object e) throws RuntimeException, InterruptedException {
    if (e == null) throw new NullPointerException("Null element is not allowed.");

    int oldCount = -1;
    putLock.lock();
    try {
      // we use count as a wait condition although count isn't protected by a lock
      // since at this point all other put threads are blocked, count can only
      // decrease (via some take thread).
      while (count.get() == capacity) notFull.await();

      queue.add((E) e);
      oldCount = count.getAndIncrement();
      //System.out.println("queue size:" + size());
      if (oldCount + 1 < capacity) {
        notFull.signal(); // notify other producers for count change
      }
    } finally {
      putLock.unlock();
    }

    // notify other waiting consumers
    if (oldCount == 0) {
      takeLock.lock();
      try {
        notEmpty.signal();
      } finally {
        takeLock.unlock();
      }
    }
  }

  public E remove() throws NoSuchElementException, InterruptedException {
    E e;

    int oldCount = -1;
    takeLock.lock();
    try {
      while (count.get() == 0) notEmpty.await();
      //System.out.println("queue size:" + size());
      e = queue.remove();
      oldCount = count.getAndDecrement();
      if (oldCount > 1) {
        notEmpty.signal(); // notify other consumers for count change
      }
    } finally {
      takeLock.unlock();
    }

    // notify other waiting producers
    if (oldCount == capacity) {
      putLock.lock();
      try {
        notFull.signal();
      } finally {
        putLock.unlock();
      }
    }

    return e;
  }

  /* Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty. */
  public E peek() {
    if (count.get() == 0) return null;

    takeLock.lock();
    try {
      return queue.peek();
    } finally {
      takeLock.unlock();
    }
  }
}
