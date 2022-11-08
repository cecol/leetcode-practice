package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC611ValidTriangleNumber extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
    }

    /**
     * https://leetcode.com/problems/valid-triangle-number/discuss/1339340/C%2B%2BJavaPython-Two-Pointers-Picture-Explain-Clean-and-Concise-O(N2)
     * two pointer - sliding window
     * 1. sort nums, 做 sliding window 常見前置條件(不是必備, 要看問題內容)
     * 2. 三角形邊長, 找出最大邊, 剩下兩邊相加大於最大邊 -> 一定成立 -> sliding window 條件
     * 3. 最難的是各種組合的加總 count 計算,
     * 比如說  2,2,3,4 -> 以 4 為最大邊, 2,2,3 哪些組合大於 4 ? (2,2), (2,3) -> 2 種組合
     * 所以window內組合總數其實是 j - i,
     * 每次找到都是 res += j - i;
     * 並非 res++ 就好
     *
     * 我一直有思考盲點是
     * res += j - i 應該是包含了 i, i+1 , .. , j 所有組合 Ex: [1,2,3,4,5] i = 1, j = 5, 1 to 5 組合
     * 如果 j--後
     * res += j - i Ex: [1,2,3,4] i = 1, j = 4, i to 4 組合
     *
     * 在 [1,2,3,4,5] i = 1, j = 5, 1 to 5 組合, j - i 是 5 跟其他的組合總數 (1,5), (2,5) .. (4,5) 剛好 4 個
     * 並沒有包含 [1,2,3,4] i = 1, j = 4, i to 4 組合, j - i 是 4 跟其他的組合總數 (1,4), (2,4) .. (3,4) 剛好 3 個
     * 所以算加總是獨立事件
     * 並沒有 [1,2,3,4,5] i = 1, j = 5, 1 to 5 組合, j - i 也包含了 4 跟其他的組合總數(1,4), (2,4) .. (3,4)
     * */
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int res = 0, n = nums.length;
        for (int k = 2; k < n; k++) {
            int i = 0, j = k - 1;
            while (i < j) {
                if(nums[i] + nums[j] > nums[k]) {
                    res += j-i;
                    j--;
                } else i++;
            }
        }
        return res;
    }
}
