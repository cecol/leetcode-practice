package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LC895MaximumFrequencyStack extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC895MaximumFrequencyStack();
    }

    /**
     * 這題太精妙了,
     * lee215 神解法 - https://leetcode.com/problems/maximum-frequency-stack/discuss/163410/C%2B%2BJavaPython-O(1)
     * 圖解 https://leetcode.com/problems/maximum-frequency-stack/discuss/1862015/BEST-Explanation-Visually
     * <p>
     * 關鍵資料結構
     * 1. 一個Map 記載當前數字出現多少次
     * 2. 一個 maxFreq 記載目前看到的最大 freq
     * 3. 最關鍵, 一個Map, Key 是出現過的 freq, Value 是出現過這個 freq 的數字 stack
     * 比如說 push(12),push(12),push(13),push(13)
     * Map 就會是 { 1 -> [12,13], 2 -> [12, 13]} 配上目前最大 freq = 2
     * 所以當拿 Map[freq = 2] 會拿到  [12, 13], 其中 13 因為是最後 push, 所以 pop max freq 也正好被拿出來
     * 所以 pop() -> 13, pop() -> 12, 這時候 max freq = 2 沒了, 變成 max freq = 1 -> 就會從 1 -> [12,13] 繼續 pop
     */
    class FreqStack {
        Map<Integer, Integer> cm = new HashMap<>();
        Map<Integer, Stack<Integer>> m = new HashMap<>();
        int max;

        public FreqStack() {

        }

        public void push(int val) {
            Integer f = cm.getOrDefault(val, 0) + 1;
            cm.put(val, f);
            max = Math.max(f, max);
            if (!m.containsKey(f)) m.put(f, new Stack<>());
            m.get(f).push(val);
        }

        public int pop() {
            Integer v = m.get(max).pop();
            cm.put(v, max - 1);
            if (m.get(max).isEmpty()) max--;
            return v;
        }
    }
}
