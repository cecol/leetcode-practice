package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;

public class LC253MeetingRoomsII extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過
    // 1. 每個會議的 start/end 要拉出來 獨立排序
    // 2. i = 0 to n, 看看當前的 start[i] < ends[endIdx] -> rooms, 當前的 end 還沒到, 又有新的 start -> room++
    // 2-1. start[i] >= ends[endIdx], 當前的 end 還沒到, 可以給 start -> endIdx++ , 檢查下一個 end
    public int minMeetingRooms(int[][] intervals) {
        int s = intervals.length;
        int[] starts = new int[s];
        int[] ends = new int[s];
        for (int i = 0; i < s; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endIdx = 0;
        for (int i = 0; i < s; i++) {
            if(starts[i] < ends[endIdx]) rooms++;
            else endIdx++;
        }
        return rooms;
    }
}
