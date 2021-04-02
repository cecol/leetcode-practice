package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.BinaryMatrix;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LC1428LeftmostColumnWithAtLeastAOne extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1428LeftmostColumnWithAtLeastAOne();
    }


    /**
     * https://leetcode.com/problems/leftmost-column-with-at-least-a-one/discuss/590828/Java-Binary-Search-and-Linear-Solutions-with-Picture-Explain-Clean-Code
     * 這題沒什麼想法, 看了人家解釋 linear 才明白其中的機巧
     * 1. 從左上角開始看
     * -> 如果是0 因為是從最上面最小row開始, 所以0的左邊一定都是0 往下找
     * -> 如果是1 往下找沒意義, 要得到更小的結果就是往左邊找 0
     * <p>
     * 也是 binary search解法, 可能更為直觀
     * 1. 其實就是找最後一個row的最左 col 是1
     * 2. 用 binary search 去找
     * <p>
     * 當然也可以線性去找, 每個col去確認, 但可能頭尾超長之類的吧
     */
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dd = binaryMatrix.dimensions();
        int m = dd.get(0), n = dd.get(1);
        int res = -1, r = 0, c = n - 1;
        while (r < m && c >= 0) {
            if(binaryMatrix.get(r,c)==1) {
                res = c;
                c--;
            } else r++;
        }
        return res;
    }
}
