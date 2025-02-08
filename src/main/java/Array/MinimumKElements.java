package Array;

import java.util.PriorityQueue;

/**
 * input k, output the k smallest elements
 * 1,2,3,4,5,6,7,8. k=4. output=1,2,3,4
 */
public class MinimumKElements {
  public static Integer[] getKSmallest(int[] data, int k) {
    // a - b is default, means minheap
    // b - a means maxheap
    PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
    for (int val : data) {
      heap.offer(val);
      if (heap.size() > k) {
        // key operation:
        // remove the root, if this is minheap, then it removes small value, keep k elements in the heap which eventually is the top biggest K elements, root is the Kth biggest
        // if this is maxheap, then it remove the largest elements, then the remaining elements is the top smallest k elements, root is the Kth smallest elements
        heap.poll();
      }
    }
    return heap.toArray(new Integer[0]);
  }
  public static void main(String[] args) {
    int[] input = new int[] {1,2,3,4,5,6,7};
    Integer[] result = MinimumKElements.getKSmallest(input, 4);
    for (int x = result.length -1; x >= 0; x--) {
      System.out.println(result[x]);
    }
  }
}
