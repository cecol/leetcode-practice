package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC899OrderlyQueue extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC899OrderlyQueue();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/blob/master/Math/899.Orderly-Queue/899.Orderly-Queue.cpp
     * 這題難在你要想通當 k <= 2 時候 等同於你可以任意 sorting string
     * 當 K >= 2
     * 你可以先把最小 char x 一路換到第一個
     * 還有一個空間 把第二小 char y 換到最後一個
     * 再把 x 換到 y 後面,
     * 這樣重複就可以一路換到 由最小 char 到大
     *
     * 只有在 k == 1 時候
     * 才會只能一個一個換 看哪個最小回傳
     */
    public String orderlyQueue(String s, int k) {
        if (k >= 2) {
            char[] cs = s.toCharArray();
            Arrays.sort(cs);
            return new String(cs);
        }
        String res = s;
        for (int i = 0; i < s.length(); i++) {
            s = s.substring(1) + s.charAt(0);
            if (s.compareTo(res) < 0) res = s;
        }
        return res;
    }
}
