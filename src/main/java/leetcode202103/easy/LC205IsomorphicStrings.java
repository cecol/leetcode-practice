package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC205IsomorphicStrings extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC205IsomorphicStrings();
    }

    /**
     * 花了許久才看懂題目意思
     * 就是說不能 兩個字對到同一個
     * aba -> acb -> a to a & b -> invalid
     * abc -> aba -> a & c to a -> invalid
     * 與其用 HashMap來做 不如直接用 int[512]
     * 因為他是ASCII 256字元, 前半一邊存 s 後半存 t
     * i 從 0 to s.length()
     * 1. 如果都沒遇過的字元 -> m[s] == m[t + 256] == 0
     * -> 那就給 i+1 (因為i從0開始, 不能直接給i, 不然會與int[]初始value conflict)
     * 2. 遇過的字元 -> m[s] == m[t + 256] == 之前給過的 i+1
     * 3. 如果都不是上面的 代表 m[s], m[t] 個別被設定過 就會知道被用過了
     */
    public boolean isIsomorphic(String s, String t) {
        int[] m = new int[512];
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if (m[sc] != m[tc + 256]) return false;
            m[sc] = m[tc + 256] = i + 1;
        }
        return true;
    }
}
