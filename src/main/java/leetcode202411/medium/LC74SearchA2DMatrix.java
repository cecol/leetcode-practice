package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

public class LC74SearchA2DMatrix extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過
    // 1. 可以攤平執行 BINARY SEARCH, row = 4, col = 3
    // 2. m[2][1] -> 2*3+1 = 7 , 7/3 == 2, 7%3 == 1
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = m * n - 1;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (matrix[mid / n][mid % n] >= target) j = mid;
            else i = mid + 1;
        }
        return matrix[j / n][j % n] == target;
    }
}
