package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LC1156SwapForLongestRepeatedCharacter extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1156SwapForLongestRepeatedCharacter();
        LC.maxRepOpt1("ababa");
        LC.maxRepOpt1("aaabaaa");
        LC.maxRepOpt1("aaabbaaa");
    }

    /**
     * 這題也是沒有什麼特定的解題pattern, 大致上查了一下解法
     * 有sliding window解法, 但是很慢, 因為要maintain a TreeMap -> O(nlogn)
     * https://leetcode.com/problems/swap-for-longest-repeated-character-substring/discuss/912864/Concise-Java-Solution-O(n)-via-Sliding-Window
     * 另一個比較直接的
     * https://leetcode.com/problems/swap-for-longest-repeated-character-substring/discuss/355875/Java-solution-with-very-detail-explanation-O(n)-Time-beat-100
     * 1. collect every repeated character
     * 2. get all characters total count
     * 3. calculate 2 cases
     * -> 1. aaabba, find another a to replace b to increase maxlen+1; so result would be aaaabb.
     * -> 2. aabaaba, firstly find the middle char b, length equals to 1,
     * ->    then make sure the left side and right side character are the same,
     * ->    then find if there is another a to replace b, update maxLen accordingly.
     * 我是沒想到這題解法是真的要自己建立個custom class來處理
     */
    public int maxRepOpt1(String text) {
        int[] freq = new int[26];
        List<int[]> repeated = new ArrayList<>();
        char pre = text.charAt(0);
        int begin = 0;
        for (int i = 0; i < text.length(); i++) {
            freq[text.charAt(i) - 'a']++;
            if (pre != text.charAt(i)) {
                repeated.add(new int[]{begin, i - 1});
                begin = i;
                pre = text.charAt(i);
            }
        }
        repeated.add(new int[]{begin, text.length() - 1});
        int res = 0;
        for (int i = 0; i < repeated.size(); i++) {
            int[] rr = repeated.get(i);
            char a = text.charAt(rr[0]);
            int len = rr[1] - rr[0] + 1;
            if (len < freq[a - 'a']) len++;
            res = Math.max(res, len);
        }

        for (int i = 1; i < repeated.size() - 1; i++) {
            int[] r1 = repeated.get(i - 1);
            int[] r2 = repeated.get(i);
            int[] r3 = repeated.get(i + 1);
            if (r2[0] == r2[1] && text.charAt(r1[0]) == text.charAt(r3[0])) {
                int len = r1[1] - r1[0] + 1 + r3[1] - r3[0] + 1;
                if (len < freq[text.charAt(r1[0]) - 'a']) len++;
                res = Math.max(res, len);
            }
        }
        return res;
    }
}
