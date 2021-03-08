package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC448FindAllNumbersDisappearedInAnArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC448FindAllNumbersDisappearedInAnArray();
    }

    /**
     * space O(N) 是很好解, 但是 space O(1) 真的是很難
     * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/discuss/777978/Java-time-O(n)-space-O(1)
     * 其實題目有幾個細節特點 才可以容易製作出space O(1)解法
     * 1. 每個重複只有出現1次
     * 2. nums裡面的數字只會是 1 -> n
     * 所以反過來思考, 把每一個 nums裡面數字當作是 index ->
     * for (int n : nums) if (nums[Math.abs(n) - 1] > 0) nums[Math.abs(n) - 1] *= -1;
     * 只要 nums[Math.abs(n)-1] 沒被set過, 就一定是 > 0 才要被set, (需要Math.abs() 是因為當前這格可能被更前面的set過了)
     * -> 但是當前被set的這格, 可能還是指向還沒被set過的位置
     * nums[沒有出現過的數字] 這個位置一定不會變成負數
     * 我原本以為 只要 nums[n-1] 改成某個值就好了, 但這樣會蓋掉nums[n-1]原本值 -> 該原本值還沒被確認過
     * 所以只有負數才是代表被set過且還可以還原回來用
     * 這是因為這題的數值區間是 1 -> n, 沒有 0 沒有負數, 所以可以用負數 encode這些資訊
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int n : nums) if (nums[Math.abs(n) - 1] > 0) nums[Math.abs(n) - 1] *= -1;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) if (nums[i] > 0) res.add(i + 1);
        return res;
    }
}
