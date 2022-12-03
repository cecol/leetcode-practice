package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class LC636ExclusiveTimeOfFunctions extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/exclusive-time-of-functions/discuss/153497/Java-solution-using-stack-wrapper-class-and-calculation-when-pop-element-from-the-stack.
     * 這題是很直觀用 stack,
     * 1. start time 放入 stack
     * 2. end time, stack.pop, 結算區間
     * - 但是有個細節是, 怎麼計算更早的 終結時間?
     * - p1.start -> p2.start -> p2.end -> p1.end
     * - p2.end - p2.start 是對的, 但 p1.end - p1.start, 會包含 p2 區間
     * - 關鍵是先做預扣 ！！, 就是在拿到 p2.end, 計算 p2 時候, 因為 p1 還沒結算 還在 stack,
     * - 所以 res[p1] 就是先預扣 res[p1] -= p2.end - p2.start ,
     * - 這樣之後遇到 p1.end 算 res[p1] += p1.end - p1.start
     * - 就會把預扣的抵銷
     * */
    public int[] exclusiveTime(int n, List<String> logs) {
        Stack<int[]> sk = new Stack<>();
        int[] res = new int[n];
        for (String log : logs) {
            String[] sp = log.split(":");
            int id = Integer.parseInt(sp[0]);
            int t = Integer.parseInt(sp[2]);
            if (sp[1].equals("start")) {
                sk.push(new int[]{id, t});
            } else {
                int[] top = sk.pop();
                res[top[0]] += t - top[1] + 1;
                if (sk.size() > 0) res[sk.peek()[0]] -= t - top[1] + 1;
            }
        }
        return res;
    }
}
