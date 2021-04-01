package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class LC7ReverseInteger extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC7ReverseInteger();
        LC.reverse(1534236469);
    }

    /**
     * https://leetcode.com/problems/reverse-integer/discuss/4060/My-accepted-15-lines-of-code-for-Java
     * 我是有解出來, 但還特地用 try catch 來處理 overflow 問題
     * 結果其實要檢查 overflow蠻簡單的
     * 1. x%10 來取最後一個值
     * 2. int newRes = res * 10 + tail;
     * -> 但先用一個 newRes 紀錄前一次結果加上新的尾數, 如果 newRes 算回去不等於前一次結果 res -> overflow (因為是從尾巴加過去, 所以overflow後就是負數)
     * 3. 如果沒有 overflow -> x/=10; 刪掉最後一個, 繼續下一圈直到 x == 0
     */
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int tail = x % 10;
            int newRes = res * 10 + tail;
            if ((newRes - tail) / 10 != res) return 0;
            res = newRes;
            x /= 10;
        }
        return res;
    }

    public int reverseOld(int x) {
        String s = Integer.toString(x);
        StringBuilder sb = new StringBuilder();
        int sign = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (Character.isDigit(s.charAt(i))) {
                if (s.charAt(i) == '0' && sb.length() == 0) continue;
                else sb.append(s.charAt(i));
            } else {
                sign = -1;
            }
        }
        try {
            return sb.length() > 0 ? Integer.parseInt(sb.toString()) * sign : 0;
        } catch (Exception e) {
            return 0;
        }
    }
}
