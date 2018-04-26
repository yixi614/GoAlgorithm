package Array;

public class mergeInterval {
  /*
  public List<Interval> mergeIntervals(List<Interval> intervals) {
    if (intervals.size() <= 1) {
      return intervals;
    }
    intervals.sort((i1, i2)->Integer.compare(i1.start, i2.start));
    List<Interval> result = new LinedList<Interval>();
    int start = intervals.get(0).start;
    int end = intervals.get(0).end;
    for (Interval interval : intervals) {
      if (interval.start <= end) { // overlapping intervals, update the end
        end = Math.max(end, interval.end);
      } else { // disjoint intervals, add previous one and update bounds
        result.add(new Interval(start, end));
        start = interval.start;
        end = interval.end;
      }
    }
    // add the last one
    result.add(new Interval(start,end));
  }
  * */
}
