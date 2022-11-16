package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC2468SplitMessageBasedOnLimit extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC2468SplitMessageBasedOnLimit();
    }

    /**
     * https://leetcode.com/problems/split-message-based-on-limit/discuss/2807831/JAVA-soln-with-explanation
     * 沒想出怎麼有效率去算 part (前一題花太多時間切也沒解出來)
     * <p>
     * 關鍵在怎麼算 part 達成題目要求
     * 解法其實很直觀, part 從 1 到 len 嘗試算算看, 有找到就算有
     * 每一輪算算看 把 part suffix 加總下去 切成該 part 字段, 是否 每一字段長度 <= limit
     * 到底要如何算該 part total suffix?
     * 加設 part = 123
     * int totalCharCount = 123(parts) * 3; <A/123> 中 低消字元: </> 共出現 123 parts 次
     * totalCharCount += (Integer.toString(part).length()) * part; <A/123> 中 低消字元: 3*123 字串長度
     * partA.append(part);
     * 這行最關鍵, 因為要累計分子A(<A/123>)字串長度 1,2,3,4,5 ... 直到 123 (<123/123>)
     * 因為 part 是從 1 to 123, 前面一直 append, 所以 當前 partA 已有了 1,2,3.... 122,
     * totalCharCount += partA.length(); 所以這邊分子長度直接加上去 就是所有 suffix 低消長度
     *
     * float remainChars = (float) len + totalCharCount; message 所有字元加上去
     * if (remainChars / (float) part <= (float) limit) break; 檢查是否真的切下去後真的 <= limit, 可以的話就 去切
     */
    public String[] splitMessage(String message, int limit) {
        int len = message.length(), part;
        StringBuilder partA = new StringBuilder();
        for (part = 1; part <= len; part++) {
            int totalCharCount = part * 3;
            totalCharCount += (Integer.toString(part).length()) * part;
            partA.append(part);
            totalCharCount += partA.length();
            float remainChars = (float) len + totalCharCount;

            if (remainChars / (float) part <= (float) limit) break;
        }

        if (part > len) return new String[]{};
        String[] res = new String[part];
        int idx = 0;
        for (int i = 1; i <= part; i++) {
            StringBuilder suffix = new StringBuilder();
            suffix.append("<").append(i).append("/").append(part).append(">");
            StringBuilder prefix = new StringBuilder();
            if (idx + limit - suffix.length() <= len)
                prefix.append(message.substring(idx, idx + limit - suffix.length()));
            else prefix.append(message.substring(idx, len));
            idx += prefix.length();
            prefix.append(suffix);
            res[i - 1] = prefix.toString();
        }
        return res;
    }
}
