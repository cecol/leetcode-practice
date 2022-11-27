package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC159LongestSubstringWithAtMostTwoDistinctCharacters extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] count = new int[256];
        int disCount = 0;
        int i = 0, j = 0;
        int res = 0;
        while (j < s.length()) {
            count[s.charAt(j)]++;
            if(count[s.charAt(j)] == 1) disCount++;
            while(disCount > 2) {
                count[s.charAt(i)]--;
                if(count[s.charAt(i)] == 0) disCount--;
                i++;
            }
            res = Math.max(res, j-i+1);
            j++;
        }
        return res;
    }
}
