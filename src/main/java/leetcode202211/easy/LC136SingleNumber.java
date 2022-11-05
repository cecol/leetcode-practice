package leetcode202211.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC136SingleNumber extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC136SingleNumber();
    }

    /**
     * 初見殺, 只能 O(n) + constant space
     * 卡了一下, 才想到 XOR 特性
     * 不過之前是有類似經驗就是
     * */
    public int singleNumber(int[] nums) {
        int x = 0;
        for(int n:nums) x = x ^ n;
        return x;
    }
}
