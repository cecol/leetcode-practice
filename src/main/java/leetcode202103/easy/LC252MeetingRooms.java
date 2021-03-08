package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC252MeetingRooms extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC252MeetingRooms();
    }

    public boolean canAttendMeetings(int[][] intervals) {
        if(intervals == null || intervals.length <= 1) return true;
        Arrays.sort(intervals, (i1, i2) -> i1[1] - i2[1]);
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) return false;
            else end = intervals[i][1];
        }
        return true;
    }
}
