package Array;

// the majority element is the element that appears more than |_n/2_| times
public class MajorityElement {
    public static int majorityElement(int[] nums) {
        int count = 0, ret = 0;
        for (int num : nums) {
            if (count == 0) {
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
        int[] els = {2, 2, 1, 1, 1, 2, 2, 3, 3, 2, 2};
        System.out.println(majorityElement(els));
    }
}
