package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.TreeSet;

public class LC681NextClosestTime extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC681NextClosestTime();
    }

    /**
     * 花了很多時間才解出來
     * 1. 題目沒看清楚, 一直submit 看錯誤test case才明白我根本沒有搞懂題目, 代表我對 Time 題型還不熟, 很難想到 corner case
     * 2. 重點是每一個值都可以拿來重複取代, 直到找到最近的
     * https://leetcode.com/problems/next-closest-time/discuss/107773/Java-AC-5ms-simple-solution-with-detailed-explaination
     * 1. 先把所有時間單位 sort
     * 2. findNext() -> 針對該位置找比他大的下一個, 如果沒有(非法時間 or 跟現值一樣), 給最小值
     * 3. 開始針對各個位置做處理, 從尾巴開始才會找到 nearest next time
     * -> "HH:M_": res[4] = findNext(res[4], (char) ('9' + 1), set);  if (res[4] > time.charAt(4))  如果有改成功就是答案
     * -> 否則 res[4] 就會被 set 成最小值
     * -> "HH:_M": res[3] = findNext(res[3], '5', set); if (res[3] > time.charAt(3)) 如果有改成功就是答案
     * -> 否則 res[3] 就會被 set 成最小值
     * -> "H_:MM": res[1] = findNext(res[1], res[0] == '2' ? '3' : ('9' + 1), set);
     * -> 因為 res[0] == 2 時候, 可以的選項就是 20, 21, 22, 23, 只有 res[0] == 0 or 1 才有可能 H1 .. H9
     * -> "_H:MM": res[0] = findNext(res[0], '2', set); 很直觀, 因為從尾巴檢查過來, 只剩下這個, 代表其他都被set成 minimum
     * -> 小時只能 0 <= H_ < 3
     */
    public String nextClosestTime(String time) {
        char[] res = time.toCharArray();
        Character[] digits = new Character[]{res[0], res[1], res[3], res[4]};
        TreeSet<Character> set = new TreeSet<Character>(Arrays.asList(digits));

        res[4] = findNext(res[4], (char) ('9' + 1), set);
        if (res[4] > time.charAt(4)) return String.valueOf(res);

        res[3] = findNext(res[3], '5', set);
        if (res[3] > time.charAt(3)) return String.valueOf(res);

        res[1] = findNext(res[1], res[0] == '2' ? '3' : ('9' + 1), set);
        if (res[1] > time.charAt(1)) return String.valueOf(res);
        res[0] = findNext(res[0], '2', set);
        return String.valueOf(res);
    }

    private char findNext(char cur, char upperLimit, TreeSet<Character> set) {
        Character n = set.higher(cur);
        return n == null || n > upperLimit ? set.first() : n;
    }
}
