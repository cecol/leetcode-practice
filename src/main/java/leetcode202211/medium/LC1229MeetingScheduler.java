package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC1229MeetingScheduler extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/meeting-scheduler/solution/
     * 有解出來, 但沒有正解想法這麼清晰
     * 關鍵在找 common slot
     * common slot = max(start1, start2), min(end1, end2)
     * 如果 max(start1, start2) > min(end1, end2) 代表沒交集, 得往前移動
     * 如果有 就檢查交集區間大小
     * 然後只移動 end 比較早的元素
     */
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(slots1, (x, y) -> (x[0] - y[0]));
        Arrays.sort(slots2, (x, y) -> (x[0] - y[0]));
        for (int i = 0, j = 0; i < slots1.length && j < slots2.length; ) {
            int[] s1 = slots1[i];
            int[] s2 = slots2[j];
            int commonStart = Math.max(s1[0], s2[0]);
            int commonEnd = Math.min(s1[1], s2[1]);
            if (commonEnd - commonStart >= duration) {
                res.add(commonStart);
                res.add(commonStart + duration);
                return res;
            }
            if (s1[1] < s2[1]) i++;
            else j++;
        }
        return res;
    }
}
