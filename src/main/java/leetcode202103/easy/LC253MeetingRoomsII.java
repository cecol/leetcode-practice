package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class LC253MeetingRoomsII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC253MeetingRoomsII();
    }

    /**
     * https://leetcode.com/problems/meeting-rooms-ii/discuss/67855/Explanation-of-%22Super-Easy-Java-Solution-Beats-98.8%22-from-%40pinkfloyda
     * super fast, faster than 95.95% of Java
     * 他是interval分成
     * int[] starts = new int[intervals.length];
     * int[] ends = new int[intervals.length];
     * 個別排序完, set int endsItr = 0;
     * 去 iterate starts -> 如果 starts[i] < ends[endsItr] -> rooms++ else endsItr++
     * */
    public int minMeetingRoomsHeapFastest(int[][] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for(int i=0; i<intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endsItr = 0;
        for(int i=0; i<starts.length; i++) {
            if(starts[i]<ends[endsItr])
                rooms++;
            else
                endsItr++;
        }
        return rooms;
    }

    /**
     * heap 版優化 faster than 83.24%
     * */
    public int minMeetingRoomsHeap(int[][] intervals) {
        PriorityQueue<Integer> rs = new PriorityQueue<>();
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        rs.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            Integer earliestRoom = rs.poll();
            if(earliestRoom <= intervals[i][0]) {
                rs.offer(intervals[i][1]);
            } else {
                rs.offer(intervals[i][1]);
                rs.offer(earliestRoom);
            }
        }
        return rs.size();
    }

    /**
     * 有解開 faster than 76.73% of Java
     * 基本概念就是先把 intervals sort by start
     * 然後用一個 List<Integer> r 記載目前房間的 end time
     * 然後取出每一個 interval 來逐一檢查 List<Integer> r 是否有 end time <= interval[0] 有的話就是更新該值
     * 沒有的話就是 r.add(interval[1]) 開新房間
     * 不過跟人家用heap來做邏輯類似 就是把 List<Integer> r 改成 heap, 最上面的就是最早結束房間
     */
    public int minMeetingRooms(int[][] intervals) {
        List<Integer> r = new ArrayList<>();
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        for (int[] ii : intervals) {
            boolean f = false;
            for (int i = 0; i < r.size(); i++) {
                Integer e = r.get(i);
                if (e <= ii[0]) {
                    f = true;
                    r.set(i, ii[1]);
                    break;
                }
            }
            if (!f) r.add(ii[1]);
        }
        return r.size();
    }
}
