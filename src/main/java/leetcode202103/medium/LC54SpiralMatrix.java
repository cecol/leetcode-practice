package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC54SpiralMatrix extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC54SpiralMatrix();
    }

    /**
     * 自己想出來的辦法確實是最快的, 原本以為有更好的方式
     * 但參考解答後大家都這樣寫...
     * */
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ri = 0, rj = m - 1, ci = 0, cj = n - 1;
        List<Integer> res = new ArrayList<>();
        while (res.size() < n * m) {
            for (int i = ci; i <= cj && res.size() < n * m; i++) res.add(matrix[ri][i]);
            ri++;
            for (int i = ri; i <= rj && res.size() < n * m; i++) res.add(matrix[i][cj]);
            cj--;
            for (int i = cj; i >= ci && res.size() < n * m; i--) res.add(matrix[rj][i]);
            rj--;
            for (int i = rj; i >= ri && res.size() < n * m; i--) res.add(matrix[i][ci]);
            ci++;
        }
        return res;
    }
}
