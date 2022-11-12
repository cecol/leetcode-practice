package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC1901FindAPeakElementII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1901FindAPeakElementII();
    }

    /**
     * https://leetcode.com/problems/find-a-peak-element-ii/discuss/1276556/JavaPythonC%2B%2B-Clear-Explanation-with-Images-or-M*Log(N)-or-Very-Short-code
     * 從 162.Find-Peak-Element (H-) 進階成 2D array 找 peak
     * 重點是降維成 1D, 就可以依照 162 邏輯來解:
     * 找出每個 col max, 組成 [col1Max, col2Max, col3Max, ... colNMax] colMax array
     * 在這個 colMax 拿去找Find-Peak-Element 就是答案
     * 因為 colMax 找到的 peak 就是 mat[i][j],    j-1 < j && j > j+1
     * 又原本 j 是該 col max -> 所以也本來就是 row 的 max
     * <p>
     * 問題怎麼找到 col_i_Max ? 如果走遍 mat[i][j], 那不如就直接拿MAX 就好, 但時間複雜度是 O(MN)
     * 所以不要取 col_i_max, 取 col_i_mid_max
     * 等於說不是先每個 col_i 都取max, 而是直接 binary search 找 col max peak 時, 在找該 mid col max row 就好
     * 只要 mid col max row > mid col - 1 && mid col max row > mid col + 1 就好
     * 根本不用預取 mid col - 1 row max &&  mid col + 1 row max
     */
    public int[] findPeakGrid(int[][] mat) {
        int startCol = 0, endCol = mat[0].length - 1;
        while (startCol <= endCol) {
            int maxRow = 0, midCol = startCol + (endCol - startCol) / 2;
            for (int r = 0; r < mat.length; r++) {
                maxRow = mat[r][midCol] > mat[maxRow][midCol] ? r : maxRow;
            }

            boolean leftIsBig = midCol - 1 >= startCol && mat[maxRow][midCol - 1] > mat[maxRow][midCol];
            boolean rightIsBig = midCol + 1 <= endCol && mat[maxRow][midCol + 1] > mat[maxRow][midCol];

            if (!leftIsBig && !rightIsBig) return new int[]{maxRow, midCol};
            else if (rightIsBig) startCol = midCol + 1;
            else endCol = midCol - 1;
        }
        return null;
    }
}
