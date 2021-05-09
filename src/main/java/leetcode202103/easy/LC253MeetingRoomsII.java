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
     * 第二次沒有解出來, 好好參考人家的解法, 才理解為什麼會先分成
     * start, end來看 並且各自sort, 看起來各自sort會造成順序錯亂, 但事實上, end一定晚於 start, 因此
     * end[0] 一定大於 start[0], 因為每一個 end都配有一個比他早的 start
     * 再來就是邏輯關鍵
     * if(starts[i]<ends[endsItr]) rooms++; else endsItr++;
     * starts[i]<ends[endsItr] 代表的是 當前starts[i]的時間還早於當前ends[endsItr] -> 代表 rooms++
     * starts[i]>=ends[endsItr] 代表的是前面的會議室end time已經有空缺出來, 可以給當前的 starts[i]用, 看下一個end time
     * 其實每次的  endsItr++; 可以視為有一間房間空缺出來, 所以當下 room 不必 ++
     * 而每次的 i++ 都是要個會議室
     *
     * 我其實一直對於為什麼 start, end 可以分開來 sort 很不能理解, 儘管解過很多次了
     * 但後來看到新的解釋好像有比較進入狀況
     * 1. What the algorithm is doing is checking how many meetings begin before the earliest-ended meeting ends.
     * -> If, for instance, 3 meetings have started before the earliest possible meeting end, than we need 3 rooms.
     * -> 就是這個演算法檢查的是, 在最早結束的meeting 之前有多少個 meet begin -> 就是要開多少room, 所以才要排序
     * 2. Sorting the arrays helps in two things: first of all you can easily get the earliest meetings end time,
     * -> and secondly, it allows you to start looking for meetings ends only from next element in the ends array
     * -> when you find some meeting start that is after the current end,
     * -> because all other meeting ends before the current in the sorted array will also be before the current meeting start.
     * -> So you just have to run 1 time over each array.
     * -> 1. 排序要幫助的就是, 先拿到在最早結束的meeting 之前有多少個 meet begin
     * -> 2. 當meeting begin > current meet end -> 代表meeting begin 也大於其他更前面的 meet end
     * -> 所以再往下找下個 end 來找出是否是有 start < end 導致還要 + room
     * 我覺得這個演算法好像只能應用在這題, 且他有個重點: end不一定要走完, 因為演算法是要看有多少 start < end 來決定開房間
     * start走完了就是答案
     *
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
