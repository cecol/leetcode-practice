package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC240SearchA2DMatrixII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC240SearchA2DMatrixII();
    }

    /**
     * 花很多時間處理 corner case也沒處理好
     * https://leetcode.com/problems/search-a-2d-matrix-ii/discuss/66140/My-concise-O(m%2Bn)-Java-solution
     * 看了答案發現只要從右上角找就好, 根本不特別用什麼 binary search? faster than 100.00%
     * 有看到很複雜解法, 但沒細看了
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return false;
        int col = matrix[0].length - 1;
        int row = 0;
        while (col >= 0 && row <= matrix.length - 1) {
            if(matrix[row][col] == target) return true;
            else if(target < matrix[row][col]) col--;
            else if(target > matrix[row][col]) row++;
        }
        return false;
    }
}
