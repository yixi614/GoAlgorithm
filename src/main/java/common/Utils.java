package common;

import java.util.Random;

public class Utils {
  public static int[] generateRandomUniqueNumbers(int k, int maxNumber) {
    int[] a = new int[maxNumber];
    int[] r = new int[k];
    for(int i = 0; i < maxNumber -1; i++) {
      a[i] = i;
    }
    int j = 0;
    for (int i = 0; i < k; i++) {
      j = randint(i, maxNumber);
      swap(a, i, j);
      r[i] = a[i];
      //debug
      //System.out.println(r[i]);
    }
    return r;
  }

  public static void swap(int[] x, int i, int j) {
    int temp = x[i];
    x[i] = x[j];
    x[j] = temp;
  }

  public static int randint(int low, int high) {
    Random r = new Random(System.currentTimeMillis());
    return low + r.nextInt(high - low);
  }

  public static void main(String[] args) {
    generateRandomUniqueNumbers(1000000, 1000);
  }
}
