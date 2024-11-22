package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC169MajorityElement extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 有印象這題有演算法, 但想不起來
    // 回去看舊筆記才想起來, major + 1 一個 counter 就足夠, 遇到不是當前 major, c--, 是的話 c++, 如果 c == 0, major 更新
    // 主因: 你是 major, 後面 c++ -- ;也會長回來, 你不是, 就讓給其他人吧
    public int majorityElement(int[] nums) {
        int major = nums[0];
        int c = 1;
        for (int i = 1; i < nums.length; i++) {
            if (c == 0) {
                major = nums[i];
                c = 1;
            } else if (major == nums[i]) c++;
            else c--;
        }
        return major;
    }

}
