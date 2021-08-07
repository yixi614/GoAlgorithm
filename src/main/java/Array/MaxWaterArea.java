package Array;

/**
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water (blue section) the container can contain is 49.
 * */
public class MaxWaterArea {
  public int maxArea(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int maxArea = 0;
    while (left < right) {
      // Always move the shorter vertical lines ahead can get a potential bigger area value
      // Because (right - left) is becomming smaller and smaller.
      int area = (right - left) * Math.min(height[left], height[right]);
      maxArea = Math.max(area, maxArea);
      if (height[left] < height[right]) {
        left++;
      } else {
        right--;
      }
    }
    return maxArea;
  }
}
