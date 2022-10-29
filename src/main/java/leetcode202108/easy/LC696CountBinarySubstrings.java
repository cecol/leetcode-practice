package leetcode202108.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class LC696CountBinarySubstrings extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC696CountBinarySubstrings();
    }

    /**
     * https://leetcode.com/problems/count-binary-substrings/discuss/108625/JavaC%2B%2BPython-Easy-and-Concise-with-Explanation
     * 沒想出答案, 我以為用 two pointer, 但我沒想出如何合理的移動i
     * 直到看到答案才發現可以這麼簡單的去思考
     * 因為關鍵是 grouped consecutively. 所以就是先去計數每一個 0 , 1 的 consecutive group
     * "0110001111" will be [1, 2, 3, 4] -> 然後每兩兩取最小值, 所以是 min(1,2) + min(2,3) + min(3,4) = 6
     * "0001111", will be min(3, 4) = 3, ("01", "0011", "000111")
     * 這樣才會把所有的substring都紀錄其中
     */
    public int countBinarySubstrings(String s) {
        int cur = 1, pre = 0, res = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) cur++;
            else {
                res += Math.min(pre, cur);
                pre = cur;
                cur = 1;
            }
        }
        return res + Math.min(cur, pre);
    }
}
