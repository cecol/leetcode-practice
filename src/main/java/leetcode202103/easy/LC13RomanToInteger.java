package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LC13RomanToInteger extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC13RomanToInteger();
        LC.romanToInt("III");
    }

    /**
     * https://leetcode.com/problems/roman-to-integer/discuss/6509/7ms-solution-in-Java.-easy-to-understand
     * 這真的太酷了, 建立個new int[s.length()], 然後先把對應的每個字元整數塞進 nums[i]
     * 然後走 i=0 to n-2 ->
     * if (nums[i] < nums[i + 1]) sum -= nums[i];
     * else sum += nums[i];
     * 最後 sum + nums[nums.length - 1];
     * 實在高明
     */
    public int romanToIntFast(String s) {
        int nums[] = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'M':
                    nums[i] = 1000;
                    break;
                case 'D':
                    nums[i] = 500;
                    break;
                case 'C':
                    nums[i] = 100;
                    break;
                case 'L':
                    nums[i] = 50;
                    break;
                case 'X':
                    nums[i] = 10;
                    break;
                case 'V':
                    nums[i] = 5;
                    break;
                case 'I':
                    nums[i] = 1;
                    break;
            }
        }
        int sum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1])
                sum -= nums[i];
            else
                sum += nums[i];
        }
        return sum + nums[nums.length - 1];
    }

    /**
     * j用很直接的邏輯計算是會過, 但速度比較慢, 看別人答案才覺得真的是我想得太簡單了
     */
    Map<Character, Integer> roman = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
    );
    Map<Character, List<Character>> sub = Map.of(
            'I', List.of('V', 'X'),
            'X', List.of('L', 'C'),
            'C', List.of('D', 'M')
    );

    public int romanToInt(String s) {
        if (s.length() == 0) return 0;
        if (s.length() == 1) return roman.get(s.charAt(0));
        int r = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (sub.keySet().contains(c) && i < s.length() - 1 && sub.get(c).contains(s.charAt(i + 1))) {
                r += roman.get(s.charAt(i + 1)) - roman.get(c);
                i++;
            } else {
                r += roman.get(c);
            }
        }
        return r;
    }
}
