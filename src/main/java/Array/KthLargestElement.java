package Array;

import java.util.PriorityQueue;

public class KthLargestElement {
  private void swap(int[] data, int i, int j) {
    int temp = data[i];
    data[i] = data[j];
    data[j] = temp;
  }

  private int partition(int[] arr, int begin, int end) {
    int pivot = arr[begin];
    int i;
    // pos always points to the latest known element that <= pivot
    int pos = begin;
    for (i = begin + 1; i <= end; i++) {
      if (arr[i] <= pivot) {
        // found one smaller element, pos should move to the next element which > pivot
        pos++;
        swap(arr, i, pos);
      }
    }
    swap(arr, begin, pos);
    return pos;
  }

  public int partition2(int[] data, int start, int end) {
    int i = start;
    int j = end;
    while (true) {
      while (i <= end && data[i] <= data[start]) {i++;}
      while(j >= start && data[j] > data[start]) {j--;}
      if (i >= j) {break;}
      this.swap(data, i, j);
    }
    this.swap(data, start, j);
    return j;
  }

  //using heap
  public int getKthLargest(int[] data, int k) {
     PriorityQueue<Integer> minHeap = new PriorityQueue<>();
     for (int val : data) {
       minHeap.offer(val);
       if (minHeap.size() > k) {
         minHeap.poll();
       }
     }
     return minHeap.peek();
  }
  // using partition
  public int getKthLargetst2(int[] data, int k) {
    int toFindKIndex = data.length - k;
    int start = 0;
    int end = data.length -1;
    while (start < end) {
      int i = partition2(data, start, end);
      if (i < toFindKIndex) {
        start = i + 1;
      } else if(i > toFindKIndex){
        end = i - 1;
      } else {
        break;// i = toFindK found Kth largest
      }
    }
    return data[toFindKIndex];
  }

  public static void main(String[] args) {
    int[] a = {1,3,2,5,7,4,8};
    int k = 2;
    // expect return 7
    KthLargestElement c = new KthLargestElement();
    int res = c.getKthLargest(a, k);
    int res2 = c.getKthLargetst2(a, k);
    System.out.println(res);
    System.out.println(res2);
  }

}
