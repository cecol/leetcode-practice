package leetcode202104.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

public class LC1153StringTransformsIntoAnotherString extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1153StringTransformsIntoAnotherString();
    }


    /**
     * 這題是Mock 遇到的, 可是我一直沒有搞懂題目的conversion的意思, 是有限制, 但題意很不清楚,
     * 我懷疑這題是要考你當下能不能把細節問出來
     * https://leetcode.com/problems/string-transforms-into-another-string/discuss/355382/JavaC%2B%2BPython-Need-One-Unused-Character
     * 大概認真看下裡面解釋不可能完成轉換的 cases
     * 1. 1對多轉換 -> 這很容易理解
     * 2. 出現 cycle, 有沒有辦法有 tmp拿來打斷cycle -> 這邊我看很久才完全理解
     * -> ex: ab 竱 ba -> 我們可以建出 conv map: a->b, b->a 有 cycle
     * -> 但題意的conversion運作是當次 conversion 是所有字元一起轉換, 所以比如先換 a->b 得到 bb
     * -> "In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character."
     * -> 然後再換 b, 會得到 bb -> aa, 不能單換某個b, 所以需要 tmp字元變成中間值
     * -> a -> b -> tmp -> a, 所以可以變成 ab -> a[tmp] -> b[tmp] -> ba
     * 1. 所以要建立 Map<Character, Character> 這個我有想到
     * 但對於 cycle 情況我是沒有好好理解的
     * 2. 針對 cycle 其實不是檢查, 而是看 Map<Character, Character> 的 values -> 被用到的 values 是否小於 26
     * -> 全部被用完 values.size == 26, 一定有 cycle -> 且沒有 tmp可以用來斷 cycle
     * -> 沒被用完, 可能有 cycle, 可能沒有, 但一定有 tmp 可以用來斷 cycle
     */
    public boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) return true;
        Map<Character, Character> conv = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            if (conv.getOrDefault(str1.charAt(i), str2.charAt(i)) != str2.charAt(i)) return false;
            conv.put(str1.charAt(i), str2.charAt(i));
        }
        return new HashSet<>(conv.values()).size() < 26;
    }
}
