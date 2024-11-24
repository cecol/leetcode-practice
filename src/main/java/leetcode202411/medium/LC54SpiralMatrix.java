package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC54SpiralMatrix extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 有想出基本解題架構, 但忘記最關鍵的, 跳出邏輯是  res.size() < s 到處都要補上
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int up = 0, down = matrix.length - 1, left = 0, right = matrix[0].length - 1, s = matrix.length * matrix[0].length;
        while (res.size() < s) {
            for (int i = left; i <= right && res.size() < s; i++) res.add(matrix[up][i]);
            up++;
            for (int i = up; i <= down && res.size() < s; i++) res.add(matrix[i][right]);
            right--;
            for (int i = right; i >= left && res.size() < s; i--) res.add(matrix[down][i]);
            down--;
            for (int i = down; i >= up && res.size() < s; i--) res.add(matrix[i][left]);
            left++;
        }
        return res;
    }
}
