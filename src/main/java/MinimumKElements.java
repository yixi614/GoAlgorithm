/**
 * input k, output the k smallest elements
 * 1,2,3,4,5,6,7,8. k=4. output=1,2,3,4
 */
public class MinimumKElements {
  //TODO:
  //O(nlogn): cat I_FILE | sort -n | head -n K
  //O(kn): do insertion sort until k elements are retrieved.
  //O(n+klogn): Take O(n) time to bottom-up build a min-heap. Then sift-down k-1 times.
  public static void main(String[] args) {

  }
}
