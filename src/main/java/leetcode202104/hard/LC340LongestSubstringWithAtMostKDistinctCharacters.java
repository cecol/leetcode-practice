package leetcode202104.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC340LongestSubstringWithAtMostKDistinctCharacters extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC340LongestSubstringWithAtMostKDistinctCharacters();
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int i = 0;
        int[] count = new int[256];
        int uc = 0;
        int res = 0;
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            count[c]++;
            if(count[c] == 1) uc++;
            if(uc <= k) res = Math.max(res, j-i+1);
            while(uc > k) {
                char ci = s.charAt(i);
                count[ci]--;
                if(count[ci] == 0) uc--;
                i++;
            }
        }
        return res;
    }
}
