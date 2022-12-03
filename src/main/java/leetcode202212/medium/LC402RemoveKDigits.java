package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Stack;

public class LC402RemoveKDigits extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC402RemoveKDigits();
    }

    /**
     * 蠻直觀的一題, 其實把範例看完就是知道該怎麼做了
     * 維持一個 monotonic increase, 如果遇到更小的  就是把 top, pop 掉直到沒有更小
     * 比較麻煩是 edge case 處理
     * 1. StringBuilder sb 最後為空
     * 2. '0' 的處理
     * 3. 跑完 String s, k 還有剩, 從尾巴減回去
     * */
    public String removeKdigits(String s, int k) {
        if (s.length() <= k) return "0";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            while (k > 0 && sb.length() > 0 && sb.charAt(sb.length() - 1) > s.charAt(i)) {
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }
            if (s.charAt(i) > '0' || sb.length() > 0) sb.append(s.charAt(i));
        }
        while (k > 0 && sb.length() > 0) {
            k--;
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
