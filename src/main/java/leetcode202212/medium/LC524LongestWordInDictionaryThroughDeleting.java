package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class LC524LongestWordInDictionaryThroughDeleting extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC524LongestWordInDictionaryThroughDeleting();
    }


    /**
     * 跟 LC792NumberOfMatchingSubsequences 一模一樣
     * 看來找 String 的 subsequence 這個是很有效的演算法
     * 準備 int[][] next = new int[n + 1][26];
     * next[i][j] 代表 String s(i) 位置後面找 26 個字母的下個 index in String s
     * 這個要從 String s 尾巴建立回來, 因為 i-1 的結果來自繼承 i 算出來的,
     * i 的位置 要往前放到 i-1, i-2 .....
     * time complexity = O(s.length * 26)
     *
     * 接著每個 word 字串都可以從 j = 0 , j = next[j][word.charAt(i) - 'a']; 一路往後找
     * 中途遇到 -1, 就代表根本沒有 subsequence
     *
     * */
    public String findLongestWord(String s, List<String> dictionary) {
        int n = s.length();
        int[][] next = new int[n + 1][26];
        s = '#' + s;
        Arrays.fill(next[n], -1);
        for (int i = n; i >= 1; i--) {
            for (int k = 0; k < 26; k++) {
                next[i - 1][k] = next[i][k];
            }
            next[i - 1][s.charAt(i) - 'a'] = i;
        }
        String res = "";
        for (String d : dictionary) {
            boolean match = true;
            int j = 0;
            for (char c : d.toCharArray()) {
                j = next[j][c - 'a'];
                if (j == -1) {
                    match = false;
                    break;
                }
            }
            if (match) {
                if (d.length() > res.length()) res = d;
                else if (d.length() == res.length() && d.compareTo(res) < 0) res = d;
            }
        }
        return res;
    }
}
