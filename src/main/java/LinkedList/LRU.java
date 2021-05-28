package LinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * The task is to design and implement methods of an LRU cache.
 * The class has two methods get and set which are defined as follows.
 * get(x)   : Gets the value of the key x if the key exists in the cache otherwise returns -1
 * set(x,y) : inserts the value if the key x is not already present. If the cache reaches its capacity it should invalidate the least recently used item before inserting the new item.
 * In the constructor of the class the size of the cache should be intitialized.
 *
 * */
class DlinkedNode {
  int key;
  int val;
  DlinkedNode pre;
  DlinkedNode next;
}
public class LRU {
  private int capacity = 0;
  private int count = 0;
  Map<Integer, DlinkedNode> map = null;
  DlinkedNode head = null;
  DlinkedNode tail = null;
  public LRU(int capacity) {
    this.capacity = capacity;
    map = new HashMap<>();
    tail = new DlinkedNode();
    head = new DlinkedNode();
    head.next = tail;
    tail.pre = head;
  }

  public int get(int key) {
    DlinkedNode r = map.get(key);
    if (null == r) {
      return -1;
    }
    this.moveToHead(r);
    return r.val;
  }

  public void put(int key, int value) {
    DlinkedNode node = map.get(key);
    if (node == null) {
      DlinkedNode newNode = new DlinkedNode();
      newNode.val = value;
      newNode.key = key;
      map.put(key, newNode);
      this.addToHead(newNode);
      count++;
      if (count > capacity) {
        this.evict();
      }
    } else {
      node.val = value;
      this.moveToHead(node);
    }
  }

  private void moveToHead(DlinkedNode node) {
    this.removeNode(node);
    this.addToHead(node);
  }

  private void removeNode(DlinkedNode node) {
    DlinkedNode pre = node.pre;
    pre.next = node.next;
    node.next.pre = pre;
  }

  private void addToHead(DlinkedNode node) {
    DlinkedNode oldFirst = head.next;
    oldFirst.pre = node;
    node.next = oldFirst;
    node.pre = head;
    head.next = node;
  }

  private void evict() {
    DlinkedNode node = tail.pre;
    map.remove(node.key);
    this.removeNode(node);
    count--;
  }
}
