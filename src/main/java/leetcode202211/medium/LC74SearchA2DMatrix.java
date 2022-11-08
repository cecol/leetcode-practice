package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class LC74SearchA2DMatrix extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC74SearchA2DMatrix();
    }

    /**
     * linear search 竟然可以過 還是最快, 看來 test case 不夠多
     * 理論上要用 binary search 來解
     * 不過我以為是做兩次 binary search, 沒想到可以攤平直接做 binary search
     * https://leetcode.com/problems/search-a-2d-matrix/discuss/26219/Binary-search-on-an-ordered-matrix
     * 比較難的是攤平後的 offset 怎麼回溯到 i, j ?
     * i = offset / col_num, j = offset % col_num
     *  lets say you have a matrix M with 4 rows and 3 columns.
     *  When we want to access M[2][1], the way the memory address is calculated is 2*3+1 = 7
     *  2 = 7/col_num, 1 = 7%col_num
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = m * n - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (matrix[mid / n][mid % n] >= target) r = mid;
            else l = mid + 1;
        }
        return matrix[l / n][l % n] == target;
    }

    public boolean searchMatrixLinear(int[][] matrix, int target) {
        int i = 0, j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0) {
            if (matrix[i][j] == target)
                return true;
            else if (matrix[i][j] > target)
                j--;
            else if (matrix[i][j] < target)
                i++;
        }
        return false;
    }
}
