package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeMap;

public class LC352DataStreamAsDisjointIntervals extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC352DataStreamAsDisjointIntervals();
    }

    TreeMap<Integer, int[]> tm = new TreeMap<>();

    /**
     * https://leetcode.com/problems/data-stream-as-disjoint-intervals/discuss/82553/Java-solution-using-TreeMap-real-O(logN)-per-adding.
     * 這題用 TreeSet 解很快, 但要先搞清楚題意 disjoint intervals
     * disjoint intervals 就是只說大家完全不相交
     * 就是說如果新加入的數, 要併入誰, 一定是因為相交
     * TreeSet key = range start, value = int[]{range start, range end}
     * 每個 key 都是一個 disjoint interval
     * 1. if (l != null && h != null && tm.get(l)[1] + 1 == value && tm.get(h)[0] - 1 == value)
     * 加進來的數有上下界  他們是獨立兩個 interval 但剛好跟 value 都差1 , 透過加入的 value 合併
     *
     * else if (l != null && tm.get(l)[1] + 1 >= value)
     * 加進來的數有下界, 解剛好跟下界 差1
     * 1. 沒上界
     * 2. 有上界 - 但上界太遠 沒得這次一起合併 沒有 joint interval
     * 加入下界
     *
     * else if (h != null && value == tm.get(h)[0] - 1)
     * 加進來的數有上界, 解剛好跟上界 差1
     * 1. 沒下界
     * 2. 有下界 - 但下界太遠 沒得這次一起合併 沒有 joint interval
     * 加入上界
     *
     * else
     * 沒有跟什麼上下界剛好差 1
     * 自己獨立成為一個 interval
     */
    public void addNum(int value) {
        if (tm.containsKey(value)) return;
        Integer l = tm.lowerKey(value);
        Integer h = tm.higherKey(value);
        if (l != null && h != null && tm.get(l)[1] + 1 == value && tm.get(h)[0] - 1 == value) {
            tm.get(l)[1] = tm.get(h)[1];
            tm.remove(h);
        } else if (l != null && tm.get(l)[1] + 1 >= value) {
            tm.get(l)[1] = Math.max(tm.get(l)[1], value);
        } else if (h != null && value == tm.get(h)[0] - 1) {
            tm.put(value, new int[]{value, tm.get(h)[1]});
            tm.remove(h);
        } else {
            tm.put(value, new int[]{value, value});
        }
    }

    public int[][] getIntervals() {
        return new ArrayList<>(tm.values()).toArray(new int[tm.values().size()][2]);
    }
}
