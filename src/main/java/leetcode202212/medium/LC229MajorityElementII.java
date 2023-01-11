package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC229MajorityElementII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC229MajorityElementII();
    }

    /**
     * https://leetcode.com/problems/majority-element-ii/solutions/234388/majority-vote-algorithm-java/
     * 這其實也是 majority-vote-algorithm
     * https://github.com/wisdompeak/LeetCode/tree/master/Greedy/229.Majority-Element-II
     * n 裏面超過 1/3 的頂多兩個, 就想像 n 分三塊,
     * 1. n1 佔了 1/3, 剩下 2/3 其他人去分, 其他人中, 沒有人超過 1/3
     * 2. n1 佔了 1/3, n2 佔 1/3,  剩下 1/3 其他人去分, 其他人中, 更不可能有人超過 1/3
     *
     * 所以先假設找到 n1/n2, 算他們的 count, 走過 i = 0 to n-1
     * 如果是 n1, n1Count++,
     * 如果是 n2, n2Count++,
     * 如果 n1Count == 0, n1 reset
     * 如果 n2Count == 0, n2 reset
     * 如果都不是 n1Count--, n2Count--
     *
     * 這就是當前累計的 n1/n2 跟別人戶消
     * 但只要 n1/n2 還有 1/3 , n1/n2 後面還會出現,
     * 但 n1/n2 沒有 1/3 , 後面就會換成 有 1/3 的 n1/n2
     *
     * 走完之後去重新累計 找到的 n1/n2, count
     * (前面戶消的 count 是 Majority vote candidate, 是有機會超過 1/3 但還要驗證)
     * 真的 count > 1/3, 就放入 res
     *
     * 這概念可以 apply to Majority 1/4, 1/5, 1/6 ...
     * */
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        int c1 = 0, c2 = 0, n1 = 0, n2 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[n1] == nums[i]) c1++;
            else if (nums[n2] == nums[i]) c2++;
            else if (c1 == 0) {
                c1 = 1;
                n1 = i;
            } else if (c2 == 0) {
                c2 = 1;
                n2 = i;
            } else {
                c1--;
                c2--;
            }
        }

        c1 = 0;
        c2 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[n1] == nums[i]) c1++;
            else if (nums[n2] == nums[i]) c2++;
        }
        if (c1 > n / 3) res.add(nums[n1]);
        if (c2 > n / 3) res.add(nums[n2]);
        return res;
    }
}
