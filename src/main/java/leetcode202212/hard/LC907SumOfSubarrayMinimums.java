package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Stack;

public class LC907SumOfSubarrayMinimums extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC907SumOfSubarrayMinimums();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Stack/907.Sum-of-Subarray-Minimums
     * 覺得蠻不直觀且也很督節要處理
     * 1. 以 A[i] 來找屬於他的 min subArray
     * - 就是往左延伸到 A[j] < A[i] 為止
     * - 就是往右延伸到 A[k] < A[i] 為止
     * - 所以 A[i] 的 subarray 區間是 [j+1, k-1]
     * - 該區間的 subarray 個數 = (i - (j+1) + 1) * ((k-1) - i + 1) => (i-j)*(k-i)
     * - 因為要包含 A[i], 所以一定是 A[i] 左右下去對半 看左邊取幾個 右邊取幾個
     * - 轉換成 A[i] 的 preSmaller-idx-j & nextSmaller-idx-k
     * - 所以一個 Stack monotonic increase - 存 offset
     * - 如果 A[i] > A[sk.top] -> sk.top 找到 nextSmaller, sk.pop
     * - sk.top 會是 A[i] preSmaller
     * - 如果沒有 nextSmaller, 就是可以延伸到右邊界 == n
     * - 如果沒有 preSmaller, 就是可以延伸到左邊界 == -1
     * <p>
     * 最後結算 i = 0 to n-1
     * subarray count = (i - preSmaller[i]) * (nextSmaller[i] - i)
     * subarray sum = A[i] * count
     *
     */
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] nextSmaller = new int[n];
        int[] preSmaller = new int[n];
        Arrays.fill(nextSmaller, n);
        Arrays.fill(preSmaller, -1);
        Stack<Integer> sk = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (sk.size() > 0 && arr[sk.peek()] > arr[i]) {
                nextSmaller[sk.peek()] = i;
                sk.pop();
            }
            if (sk.size() > 0) preSmaller[i] = sk.peek();
            sk.push(i);
        }

        long res = 0;
        long M = (long) 1e9 + 7;
        for (int i = 0; i < n; i++) {
            int a = preSmaller[i];
            int b = nextSmaller[i];
            long num = (long) arr[i] * (i - a) % M * (b - i) % M;
            res = (res + num) % M;
        }
        return (int) res;
    }
}
