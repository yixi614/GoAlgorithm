package Array;


import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi],
 * return the minimum number of conference rooms required.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 * Example 2:
 *
 * Input: intervals = [[7,10],[2,4]]
 * Output: 1
 *
 * */
public class MeetingRoomII {
    /**
     * This solution works by:
     *
     * 1. Sorting all intervals by their start times so we process meetings in chronological order
     * 2. Using a priority queue (min heap) to keep track of the end times of all ongoing meetings
     * 3. For each meeting, we check if it can reuse a room by comparing its start time with the earliest ending meeting (the top of the min heap)
     * 4. If the meeting starts after an existing meeting ends, we can reuse that room (remove from heap)
     * 5. We always add the current meeting's end time to the heap
     * 6. The final size of the heap tells us how many rooms we need simultaneously
     * */
    public int minMeetingRooms(int[][] intervals) {
        // Check for edge cases
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Use a min heap to track the end times of meetings in progress
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add the end time of the first meeting
        minHeap.add(intervals[0][1]);

        // Process the rest of the meetings
        for (int i = 1; i < intervals.length; i++) {
            // If the current meeting starts after the earliest ending meeting
            // we can reuse that room
            if (intervals[i][0] >= minHeap.peek()) {
                minHeap.poll(); // Remove the earliest ending meeting
            }

            // Add the current meeting's end time to the heap
            minHeap.add(intervals[i][1]);
        }

        // The size of the heap represents the minimum number of rooms needed
        return minHeap.size();
    }
}
