package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC916WordSubsets extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC916WordSubsets();
    }

    /**
     * 既然是要找 universal strings
     * 就是要包含所有 word2
     * 那就是 word2 算一個 char count max, 全部都包含 - int[] w2Count
     * 然後確認 word1[i] 是否有 包含 w2Count 就好
     * */
    public List<String> wordSubsets(String[] w1, String[] w2) {
        int[] w2Count = new int[26];
        for (String w : w2) {
            int[] w2c = new int[26];
            for (char c : w.toCharArray()) w2c[c - 'a']++;
            for (int i = 0; i < 26; i++) w2Count[i] = Math.max(w2Count[i], w2c[i]);
        }
        List<String> res = new ArrayList<>();
        for (String w : w1) {
            int[] w1c = new int[26];
            for (char c : w.toCharArray()) w1c[c - 'a']++;
            boolean ok = true;
            for (int i = 0; i < 26; i++) {
                if(w1c[i] < w2Count[i]) {
                    ok = false;
                    break;
                }
            }
            if(ok) res.add(w);
        }
        return res;
    }
}
