package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC630CourseScheduleIII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC630CourseScheduleIII();
    }

    /**
     * 也不是很直觀的概念
     * https://leetcode.com/problems/course-schedule-iii/discuss/2185367/Java-or-Greedy-or-Explained
     * https://leetcode.com/problems/minimum-cost-to-hire-k-workers/discuss/141768/Detailed-explanation-O(NlogN)
     * https://github.com/wisdompeak/LeetCode/tree/master/Priority_Queue/857.Minimum-Cost-to-Hire-K-Workers
     * 基本上一定很直觀認為 快結束的 course 一定要放在前面優先被考慮
     * 所以先 sort courses by end_time
     * 然後我們開始 go through courses, 因為 courses 已經根據 end_time sort 過了
     * 所一定是越走 end_time 越大
     * 邊走邊累計目前 totalDuration 總值
     * 如果 totalDuration 總值超過 目前 end_time, 代表一定有個 course duration 太長, 根本無法修課
     * 所以得透過 max PriorityQueue 裏面放 duration, 優先拿掉 max duration
     * 所以 totalDuration -= pq.poll();
     *
     * 這樣最後 PriorityQueue 裏面放的 course duration 都是可以修完的課的 duration
     * 所以 PriorityQueue.size() == 答案
     * */
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (x, y) -> x[1] - y[1]);
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        for (int i = 0, totalDuration = 0; i < courses.length; i++) {
            pq.add(courses[i][0]);
            if ((totalDuration += courses[i][0]) > courses[i][1]) totalDuration -= pq.poll();
        }
        return pq.size();
    }
}
