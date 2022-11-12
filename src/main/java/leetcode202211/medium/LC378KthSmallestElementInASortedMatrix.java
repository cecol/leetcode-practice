package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC378KthSmallestElementInASortedMatrix extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * matrix 是 row, column 各自有 sort
     * [1, 3,  7]
     * [5, 10, 12]
     * [6, 10, 15]
     * min = matrix[0][0], max = matrix[n-1][n-1]
     * 在這區間 binary search , 找出 countLessOrEq(mid) 逼近 k
     * 有幾個關鍵細節
     * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/1322101/C%2B%2BJavaPython-MaxHeap-MinHeap-Binary-Search-Picture-Explain-Clean-and-Concise
     * https://github.com/wisdompeak/LeetCode/blob/master/Binary_Search/378.Kth-Smallest-Element-in-a-Sorted-Matrix/378.Kth-Smallest-Element-in-a-Sorted-Matrix_v2.cpp
     * 1.
     * k = 5, 給 mid = 8 會成立, 可是 mid = 8 不在 matrix 內, 所以要往下找直到找到 7
     * [(1), (3),  (7)]
     * [(5), 10,   12]
     * [(6), 10,   15]
     * 所以要找 countLessOrEq(mid) 總數,
     * 但 k = 6, mid = 10, countLessOrEq(10) = 7 > k
     * 如果再往下找 countLessOrEq(9) = 5, 又不符合 k = 6
     * 所以條件是 countLessOrEq(matrix, mid) >= k 都可能是答案, 因為是 第幾小,
     * 用 countLessOrEq 會包含自己, 因為包含自己照成 >= k 是合理的
     *
     * 2. 要如何有效計算 countLessOrEq(matrix, mid) ?
     * 因為 matrix 是 row, column 各自有 sort
     * 所以很直觀從 matrix[0 to n-1][n-1 to 0] 開始找
     * 過程中只要 matrix[r][c] > mid, c--; c 永久有效下一 row
     * count 就是 += c+1
     * */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int l = matrix[0][0], r = matrix[n - 1][n - 1], res = -1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (countLessOrEq(matrix, mid) >= k) {
                r = mid;
            } else l = mid + 1;
        }
        return l;
    }

    int countLessOrEq(int[][] matrix, int mid) {
        int n = matrix.length;
        int count = 0, c = n - 1;
        for (int r = 0; r < n; r++) {
            while (c >= 0 && matrix[r][c] > mid) c--;
            count += (c + 1);
        }
        return count;
    }
}
