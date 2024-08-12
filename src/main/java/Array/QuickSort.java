package Array;

import java.util.Arrays;

public class QuickSort {

  public void quickSort(int[] arr, int begin, int end) {
    if (begin < end) {
      int partitionIndex = partition(arr, begin, end);
      quickSort(arr, begin, partitionIndex - 1);
      quickSort(arr, partitionIndex + 1, end);
    }
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
        // i point to a value that is <= pivot, pos point to a value that is > privot.
        // Swap them so that keep "the pos always points to a value <= pivot".
        // Note that the Pivot element (index is "begin") itself doesn't change index. We'll put it to the proper position in the end
        swap(arr, i, pos);
      }
    }
    // put pivot(index is "begin") to the correct index "pos" so that all left elements are <= pivot and right elements are > pivot
    swap(arr, begin, pos);
    return pos;
  }

  private void swap(int[] arr, int i, int j) {
    int swapTemp = arr[i];
    arr[i] = arr[j];
    arr[j] = swapTemp;
  }

  public static void main(String[] args) {
    QuickSort qs = new QuickSort();
    int[] numArray = {6,3,1,4,2,10,5,9,4,0,123,43,12,0};
    qs.quickSort(numArray, 0, numArray.length - 1);
    Arrays.stream(numArray).forEach(x -> System.out.println(x));
  }

}
