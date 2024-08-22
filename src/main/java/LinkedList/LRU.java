package LinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * The task is to design and implement methods of an LRU cache.
 * The class has two methods get and set which are defined as follows.
 * get(x)   : Gets the value of the key x if the key exists in the cache otherwise returns -1
 * set(x,y) : inserts the value if the key x is not already present. If the cache reaches its capacity it should invalidate the least recently used item before inserting the new item.
 * In the constructor of the class the size of the cache should be intitialized.
 * The functions get and set must each run in O(1) average time complexity.
 * */
class LRUCache {
  public class DlinkedNode {
    int key;
    int value;
    DlinkedNode pre;
    DlinkedNode next;
    public DlinkedNode() {}
    public DlinkedNode(int k, int v) {
      this.key = k;
      this.value = v;
    }
  }
  // key to node
  HashMap<Integer, DlinkedNode> keyToNode;
  DlinkedNode head = new DlinkedNode();
  DlinkedNode tail = new DlinkedNode();
  int maxCapacity;
  public LRUCache(int capacity) {
    this.maxCapacity = capacity;
    keyToNode = new HashMap();
    head.next = tail;
    head.pre = tail;
    tail.pre = head;
    tail.next = head;
  }

  public int get(int key) {
    DlinkedNode node = keyToNode.get(key);
    if (node == null) {
      return -1;
    }
    moveToHead(node);
    return node.value;
  }

  public void put(int key, int value) {
    DlinkedNode node = keyToNode.get(key);
    if (node != null) {
      node.value = value;
      moveToHead(node);
      return;
    }
    if (keyToNode.size() == maxCapacity) {
      evict();
    }
    DlinkedNode newNode = new DlinkedNode(key, value);
    keyToNode.put(key, newNode);
    addToHead(newNode);
  }

  public void moveToHead(DlinkedNode node) {
    if (node == head.next) {
      return;
    }
    deleteNode(node);
    addToHead(node);
  }

  public void evict() {
    keyToNode.remove(tail.pre.key);
    deleteNode(tail.pre);
  }

  public void deleteNode(DlinkedNode node) {
    node.pre.next = node.next;
    node.next.pre = node.pre;
  }

  public void addToHead(DlinkedNode newHead) {
    DlinkedNode currentHead = head.next;
    newHead.pre = head;
    newHead.next = currentHead;
    currentHead.pre = newHead;
    head.next = newHead;
  }
}
