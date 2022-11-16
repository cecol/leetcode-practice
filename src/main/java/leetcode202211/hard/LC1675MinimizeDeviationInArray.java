package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeSet;

public class LC1675MinimizeDeviationInArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1675MinimizeDeviationInArray();
    }

    /**
     * https://leetcode.com/problems/minimize-deviation-in-array/discuss/952864/Java-TreeSet-nlog(n)log(m)
     * 這題核心問題是
     * even 只可以往下直到變成 odd
     * odd 只可能往上直到變成 even
     * 求對所有數操作達到最小 deviation
     *
     * 有想到用 TreeSet 來解 但沒想好說處理頭尾case
     * 其實可以很簡單的
     * 1. nums 加入 TreeSet, 如果是 even 直接加入, 如果是 odd, odd*2 加入
     * 這樣 TreeSet 裏面都是 even 只能往下 單一方向調整直到最小 deviation
     * 所以頭尾是 deviation = TreeSet.last - TreeSet.first
     * 所以只要檢查 last, last 是 even 就往下更新直到 last 是 odd 變成只能往上
     * 因為 first 是 even 也只能往下
     * */
    public int minimumDeviation(int[] nums) {
        TreeSet<Integer> st = new TreeSet<>();
        for (Integer n : nums)
            if (n % 2 == 0) st.add(n);
            else st.add(n * 2);
        int res = Integer.MAX_VALUE;
        while (true) {
            int biggest = st.last();
            res = Math.min(res, biggest - st.first());
            if (biggest % 2 == 0) {
                st.remove(biggest);
                st.add(biggest / 2);
            } else break;
        }
        return res;
    }
}
