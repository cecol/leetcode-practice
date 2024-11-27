package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC252MeetingRooms extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 直觀解, 排序 Meetings, 從 OFFSET 1 開始往前面比較, 如果出現 OVERLAP, 代表無法出席所有
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (x, y) -> {
            return x[0] - y[0];
        });
        for (int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] < intervals[i-1][1]) return false;
        }
        return true;
    }
}
