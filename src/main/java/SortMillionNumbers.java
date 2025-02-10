import Common.BitMap;
import Common.Utils;

import java.util.Arrays;

/**
 *
 * Sort unique 10 million numbers. All numbers are less than MaxNumber
 * */
public class SortMillionNumbers {
  public static void main(String[] args) {
    int maxNumber = 100000000;
    int count = 10000000;
//    int maxNumber = 20;
//    int count = 10;
    int[] a = Utils.generateRandomUniqueNumbers(count, maxNumber);
    BitMap bm = new BitMap(maxNumber);
    long start = System.currentTimeMillis();
    //System.out.println("Original numbers:");
    for (int i = 0; i < a.length; i++) {
      //System.out.println(a[i]);
      bm.set(a[i]);
    }
    //System.out.println("Sorted numbers:");
    for (int i = 0,j = 0; i < maxNumber; i++) {
      if (bm.get(i)) {
        a[j++] = i;
      }
    }
    a = null;
//    for (int i = 0; i < a.length; i++) {
//      System.out.println(a[i]);
//    }
    System.out.println("BitMap Costs:" + (System.currentTimeMillis() - start) + " ms");
    a = Utils.generateRandomUniqueNumbers(count, maxNumber);
    start = System.currentTimeMillis();
    Arrays.sort(a);
    System.out.println("Java QuickSort Costs:" + (System.currentTimeMillis() - start) + " ms");
  }
}
