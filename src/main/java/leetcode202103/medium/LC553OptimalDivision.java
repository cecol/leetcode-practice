package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC553OptimalDivision extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC553OptimalDivision();
        LC.optimalDivision(new int[]{1000, 100, 10, 2});
    }

    /**
     * 這題基本上就是數學題, 我是沒有想出來
     * https://leetcode.com/problems/optimal-division/discuss/101687/Easy-to-understand-simple-O(n)-solution-with-explanation
     * X1/X2/X3/../Xn will always be equal to (X1/X2) * Y, no matter how you place parentheses.
     * i.e no matter how you place parentheses, X1 always goes to the numerator and X2 always goes to the denominator.
     * Hence you just need to maximize Y.
     * And Y is maximized when it is equal to X3 *..*Xn. So the answer is always X1/(X2/X3/../Xn) = (X1 *X3 *..*Xn)/X2
     *
     * 基本上就是第一個一定是 分子, 無論你後面跨號怎麼擺
     * 第二個一定是分母, 第三個之後的只是用來最小化第二個, 來讓最後 X1/X2 最大化
     * 所以可以看成 X1/(X2/X3/../Xn) = (X1 *X3 *..*Xn)/X2
     *
     * 是有用非數學方式解 -> backtrack
     * https://leetcode.com/problems/optimal-division/discuss/101697/Java-Solution-Backtracking
     * 但效率真的很差, 也很複雜
     * */
    public String optimalDivision(int[] nums) {
        if(nums == null || nums.length ==0) return "";
        if(nums.length == 1) return String.valueOf(nums[0]);
        if(nums.length == 2) return String.valueOf(nums[0]) + "/" + String.valueOf(nums[1]);
        StringBuilder sb = new StringBuilder("");
        sb.append(nums[0]).append("/(").append(nums[1]);
        for(int i=2;i<nums.length;i++) {
            sb.append("/").append(nums[i]);
        }
        sb.append(")");
        return sb.toString();
    }
}
