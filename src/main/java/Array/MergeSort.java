package Array;

public class MergeSort {
  public static int[] sort(int[] a, int low, int high) {
    int mid = (low + high) / 2;
    if (low < high) {
      sort(a, low, mid);
      sort(a, mid + 1, high);
      merge(a, low, mid, high);
    }
    return a;
  }

  public static void merge(int[] a, int low, int mid, int high) {
    int[] temp = new int[high - low + 1];
    int i = low;
    int j = mid + 1;
    int k = 0;
    while (i <= mid && j <= high) {
      if (a[i] < a[j]) {
        temp[k++] = a[i++];
      } else {
        temp[k++] = a[j++];
      }
    }
    while (i <= mid) {
      temp[k++] = a[i++];
    }
    while (j <= high) {
      temp[k++] = a[j++];
    }
    for (int x = 0; x < temp.length; x++) {
      a[x + low] = temp[x];
    }
  }
}
