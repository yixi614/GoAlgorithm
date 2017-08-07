package multiThread;

/**
 * Created by ztang16 on 8/6/2017.
 */
public interface BoundedBlockingQueue<E> {
  int size();
  void add(E e) throws RuntimeException, InterruptedException;
  E remove() throws RuntimeException, InterruptedException;
  E peek();
}
