package Array;

import java.util.PriorityQueue;

public class KthLargestElement {
  private void swap(int[] data, int i, int j) {
    int temp = data[i];
    data[i] = data[j];
    data[j] = temp;
  }

  public int partition(int[] data, int start, int end) {
    int piviot = data[start];
    int pos = start;
    for (int i = start + 1; i < end; i++) {
      if (data[i] <= piviot) {
        this.swap(data,++pos, i);// ++pos make it point to the first element that >= piviot
      }
    }
    this.swap(data, start, pos);
    return pos;
  }

  public int partition2(int[] data, int start, int end) {
    int i = start;
    int j = end + 1;
    while (true) {
      while (i < end && data[i] < data[start]) {i++;}
      while(j > start && data[j] > data[start]) {j--;}
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
    int toFindK = data.length - k;
    int start = 0;
    int end = data.length -1;
    while (start < end) {
      int i = partition2(data, start, end);
      if (i < toFindK) {
        start = i + 1;
      } else if(i > toFindK){
        end = i - 1;
      } else {
        break;// i = toFindK found Kth largest
      }
    }
    return data[toFindK];
  }

}
