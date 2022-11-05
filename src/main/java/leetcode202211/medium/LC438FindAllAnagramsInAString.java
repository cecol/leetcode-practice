package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.ArrayList;
import java.util.List;

public class LC438FindAllAnagramsInAString extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC438FindAllAnagramsInAString();
    }

    /**
     * https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/146443/Java-Code-Beats-98.76-with-Explanation
     * 跟76 Minimum Window Substring 很像
     * 關鍵差別在 得 j-i+1 == p.length() 才納入結果
     * 我錯誤地方在於誤解 Anagrams, 沒有想到說是要同長度字串, 只是裡面字元錯置
     * 所以關鍵在於找 s 中 substring length == p 且出現字元總數一致
     * */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int[] cc = new int[256];
        for (char c : p.toCharArray()) cc[c]++;
        int mc = 0;
        int i = 0, j = 0;
        while (j < s.length()) {
            if (cc[s.charAt(j)] > 0) mc++;
            cc[s.charAt(j)]--;
            while (mc == p.length()) {
                if (j - i + 1 == p.length()) res.add(i);
                cc[s.charAt(i)]++;
                if (cc[s.charAt(i)] > 0) mc--;
                i++;
            }
            j++;
        }
        return res;
    }
}
