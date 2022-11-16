package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC2472MaximumNumberOfNonoverlappingPalindromeSubstrings extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC2472MaximumNumberOfNonoverlappingPalindromeSubstrings();
    }

    /**
     * https://leetcode.com/problems/maximum-number-of-non-overlapping-palindrome-substrings/discuss/2808821/Easy-Java-Solution
     * 這題蠻多解法, 看來 Palindromes 問題有特定 Manacher 演算法 但太複雜 反而沒這麼多人用
     * 反而暴力下去找 ,
     * 從 s i = 0 to s.length 開始每個
     * 1. i,i
     * 2. i, i+1
     * 下去左右擴張找最長 or 剛好 >= k 的 Palindromes
     * 有找到就 count++ 就好
     * 找到 j-i+1 >= k, 得更新現在找的起始點 start = j+1, 不然就會重複計算
     * <p>
     * 有看到 DP 還有一些奇怪解法 但都很難懂...
     */
    int count = 0;
    int start = 0;

    public int maxPalindromes(String s, int k) {
        for (int i = 0; i < s.length(); i++) {
            helper(s, i, i, k);
            helper(s, i, i + 1, k);
        }
        return count;
    }

    void helper(String s, int i, int j, int k) {
        while (i >= 0 && j < s.length() && i >= start) {
            if (s.charAt(i) != s.charAt(j)) break;
            if (j - i + 1 >= k) {
                count++;
                start = j + 1;
                break;
            }
            i--;
            j++;
        }
    }
}
