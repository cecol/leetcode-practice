package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC68TextJustification extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC68TextJustification();
    }

    /**
     * https://leetcode.com/problems/text-justification/discuss/24876/Simple-Java-Solution
     * 這題真的是很多corner case要好好處理, 沒什麼特別的邏輯要思考
     * 1. 針對每一行, greedy 找出能容納多少字元
     * -> int count = words[idx].length(); 有一個字元不用多加 space -> 所以第一個只要length 不用多加 space 1
     * -> if (words[last].length() + count + 1 > maxWidth) break; 後續每個加進來的都要確認 字數加上space是否 <= maxWidth
     * -> 確認完之後last 會是下一行的第一個, 當前這一行會是 idx, idx+1 ..., last - 1
     * int slots = last - idx - 1; -> 總共幾個字在這一行中
     * if (last == words.length || slots == 0) { -> 最後一行判斷 -> 要 left-justified or 該行只有一個單字也要 left-justified
     * -> 既然是最後一行, 就直接一個一個 append上去 for (int i = idx; i < last; i++) sb.append(words[i] + " ");
     * ->  sb.deleteCharAt(sb.length() - 1);刪掉最後一個append的多餘空白
     * ->  for (int i = sb.length(); i < maxWidth; i++) sb.append(' '); 補空白到 maxWidth
     * } else { 不是最後一行 -> fully-justified
     * -> int spaces = (maxWidth - count) / slots; 平均每個字要配多少空白
     * -> 這邊有個細節是 count 已包含slots個空白的(所以後面的j是0開始到<=spaces 把count裡面的space多補回來
     * -> 有條件是, 如果 space不能平均分散, 左邊的空白要 >= 右邊的, 所以要多算int r = (maxWidth - count) % diff;
     * -> if (i < last - 1) for (int j = 0; j <= (spaces + (i - idx < r ? 1 : 0)); j++) sb.append(' ');
     * -> 就是在說, 如果不是最後一行, 那都要補空白, 但補空白的數目是 space(整除平均) + 前r個slow還要每個各多分配一個
     * -> int r = (maxWidth - count) % slots; 這邊就是在算說, space分配完後的餘數r, r就是左邊算來前幾個slow都要多配一個空白
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int idx = 0;
        while (idx < words.length) {
            int last = idx + 1;
            int count = words[idx].length();
            while (last < words.length) {
                if (words[last].length() + count + 1 > maxWidth) break;
                count += words[last].length() + 1;
                last++;
            }
            StringBuilder sb = new StringBuilder();
            int slots = last - idx - 1;
            if (last == words.length || slots == 0) {
                for (int i = idx; i < last; i++) sb.append(words[i] + " ");
                sb.deleteCharAt(sb.length() - 1);
                for (int i = sb.length(); i < maxWidth; i++) sb.append(' ');
            } else {
                int totalSpaces = maxWidth - (count - slots);
                int spaces = totalSpaces / slots;
                int r = totalSpaces % slots;
                for (int i = idx; i < last; i++) {
                    sb.append(words[i]);
                    if (i < last - 1) {
                        for (int j = 1; j <= (spaces + (i - idx < r ? 1 : 0)); j++) sb.append(' ');
                    }
                }
            }
            res.add(sb.toString());
            idx = last;
        }
        return res;
    }
}
