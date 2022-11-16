package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class LC2276CountIntegersInIntervals extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC2276CountIntegersInIntervals();

        LC.add(2, 3);
        LC.add(7, 10);

    }

    public void CountIntervals() {

    }

    TreeMap<Integer, Integer> tm = new TreeMap<>();
    int c = 0;

    /**
     * 此題跟 715.Range-Module 一模一樣
     * 用 TReeMap 建立 interval
     * 只是在 count 那邊不能 一個一個把 TreeMap 都拿出來加總 會 TLE,
     * 所以在 add 那邊就要 邊計算目前 count
     * https://leetcode.com/problems/count-integers-in-intervals/discuss/2039706/Merge-Intervals
     */
    public void add(int left, int right) {
        while (true) {
            Map.Entry<Integer, Integer> fen = tm.floorEntry(right);
            if (fen == null || fen.getValue() < left) break;
            tm.remove(fen.getKey());
            left = Math.min(left, fen.getKey());
            right = Math.max(right, fen.getValue());
            c -= fen.getValue() - fen.getKey() + 1;
        }
        tm.put(left, right);
        c += right - left + 1;
    }

    public int count() {
        return c;
    }
}
