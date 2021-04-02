package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC394DecodeString extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC394DecodeString();
    }

    /**
     * https://leetcode.com/problems/decode-string/discuss/210284/Java-Recursive
     * 這題是可以很直觀的用 Stack來解
     * 不過沒想到可以用Deque + recursive變得更精簡
     * 幾個重點
     * 1. 如果是數字, 用一個 int n 來紀錄, 每次都是 n = n * 10 + c - '0';
     * 2. 如果遇到 '[' 就是 -> 遞迴下去, 然後拿回傳回來的String 重複n次
     * 3. 如果遇到 ']' 直接 break; 代表該層 遞迴結束
     * 4. 剩下都直接 sb.append
     */
    public String decodeString(String s) {
        Deque<Character> q = new LinkedList<>();
        for (char c : s.toCharArray()) q.offer(c);
        return h(q);
    }

    private String h(Deque<Character> q) {
        StringBuilder sb = new StringBuilder();
        int n = 0;
        while (q.size() > 0) {
            Character c = q.poll();
            if (Character.isDigit(c)) n = n * 10 + c - '0';
            else if (c == '[') {
                String sub = h(q);
                for (; n > 0; n--) sb.append(sub);
            } else if (c == ']') break;
            else sb.append(c);
        }
        return sb.toString();
    }
}
