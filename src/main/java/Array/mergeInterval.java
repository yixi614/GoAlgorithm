package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class mergeInterval {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();
        int[] prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] <= prev[1]) { // found overlap intervals, current interval's left end is small than the previous right end
                // merge operation is to update the previous interval's right end to the right end value. anyway we'll just keep one interval
                // why we don't add this new interval? Because there might be overlap intervals in the following, so we should add to list when we're sure that
                // there's no overlap
                prev[1] = Math.max(prev[1], interval[1]);
            } else { // if no overlap, add the prev interval, and move pre pointer to the next
                merged.add(prev);
                prev = interval;
            }
        }
        // add the prev which now points to the last interval
        merged.add(prev);
        return merged.toArray(new int[merged.size()][]);
    }
}
