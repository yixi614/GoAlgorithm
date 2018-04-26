package Array;

// the majority element is the element that appears more than |_n/2_| times
public class MajorityElement {
  /*
  public majorityElement(int[] nums) {
    Arrays.sort(nums);
    return num[nums.length/2];
  }

  public majorityElement(int[] nums) {
    Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();
    int ret = 0;
    for (int num : nums) {
      myMap.put(num, myMap.getOrDefault(num, 0) + 1);
      if (myMap.get(num) > nums.length/2) {
        ret = num;
        break;
      }
    }
    return ret;
  }

  public majorityElement(int[] nums) {
    int count = 0, ret = 0;
    for (int num : nums) {
      if(count==0) {
        ret = num;
      }
      if (num != ret) {
        count--;
      } else {
        count++;
      }
    }
    return ret;
  }

  * */
 // TODO: this is not right? see the main example
  public static int majorityElement(int[] nums) {
    int count = 0, ret = 0;
    for (int num : nums) {
      if(count==0) {
        ret = num;
      }
      if (num != ret) {
        count--;
      } else {
        count++;
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    int[] els = {2,2,1,1,1,2,2,3,3,2,2};
    System.out.println(majorityElement(els));
  }
}
