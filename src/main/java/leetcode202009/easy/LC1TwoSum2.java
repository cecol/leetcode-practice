package leetcode202009.easy;


import leetcode202009.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC1TwoSum2 extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC1TwoSum2();
        var s = LC.twoSum(new int[]{2, 5, 5, 11}, 10);
    }

    public int[] twoSum(int[] numbers, int target) {
        int[] r = new int[2];
        r[1] = numbers.length-1;
        while(r[0] <= r[1]) {
            int s = numbers[r[0]] + numbers[r[1]];
            if(s == target) {
                break;
            } else if(s < target) {
                r[0]++;
            } else {
                r[1]--;
            }
        }
        r[0]++;
        r[1]++;
        return r;
    }

}
