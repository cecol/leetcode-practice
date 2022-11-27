package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC360SortTransformedArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * 當然可以暴力解過, 但這樣跟題意無關了
     * 這題要 two pointer 解
     * https://leetcode.com/problems/sort-transformed-array/discuss/83322/Java-O(n)-incredibly-short-yet-easy-to-understand-AC-solution
     * nums 本來就 sorted 過了
     * 配上 函數 aX^2 + bX + c 本質是個拋物線函數(看網址裡面有附圖)
     * (因為 X 往無限大或者無限小走, X^2 會超大,  bX 只會是線性, 根本沒太多影響力, 所以 aX^2 決定一切)
     * 看起來要考慮 a>0, a=0, a<0, (when a=0, b>0, b<0) 各種 case
     * 但事實上只要看 a > 0 or a < 0
     * 先看 a != 0, X^2 本身就是拋物線, 所以 a > 0 頭尾最大, a < 0 拋物線反過來, 頭尾最小
     * 當 a == 0, bx + c 變成線性函數 linear func, min/max 還是在頭尾
     * 解釋:
     * https://leetcode.com/problems/sort-transformed-array/discuss/83322/Java-O(n)-incredibly-short-yet-easy-to-understand-AC-solution/87561
     * <p>
     * 當理解頭尾是max or min 就知道 two pointer 要怎麼開始走
     * <p>
     * If a < 0; the smallest number must be at either of the two ends of origin array;
     * If a > 0; the largest number must be at either of the two ends of origin array;
     * 所以 a > 0, 就是從 result array 尾巴開始填 -> nums 頭尾 two pointer 來縮限找 max -> next max
     * 所以 a < 0, 就是從 result array 開頭開始填 -> nums 頭尾 tow pointer 來縮限找 min -> next min
     */
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] sorted = new int[n];
        int i = 0, j = n - 1;
        int idx = a > 0 ? n - 1 : 0;
        while (i <= j) {
            if (a > 0) {
                sorted[idx--] = quad(nums[i], a, b, c) > quad(nums[j], a, b, c) ? quad(nums[i++], a, b, c) : quad(nums[j--], a, b, c);
            } else {
                sorted[idx++] = quad(nums[i], a, b, c) < quad(nums[j], a, b, c) ? quad(nums[i++], a, b, c) : quad(nums[j--], a, b, c);
            }
        }
        return sorted;
    }

    int quad(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}
