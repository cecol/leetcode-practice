package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC51NQueens extends BasicTemplate {
    public static void main(String[] args) {
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        boolean[] col = new boolean[n];
        Arrays.fill(col, false);
        putQ(res, new ArrayList<>(), n, col);
        return res;
    }

    // 沒過
    // 1. 用 boolean[] 記載 放過的 col
    // 2. 每放一個 from 0 to n, 看 col[i] && checkQ
    // 3. checkQ 是檢查 往上面的對角線 (row 不用看, 因為一次只放一個 queen)
    // 3-1. i from c.size -1, upDiff 就是 c.size - i
    // 3-2. 左上角就是  Qi - upDiff, 右上角 = Qi + upDiff
    void putQ(List<List<String>> r, List<String> current, int n, boolean[] col) {
        if (current.size() == n) {
            r.add(new ArrayList<>(current));
        } else {
            for (int newQi = 0; newQi < n; newQi++) {
                if (!col[newQi] && checkQ(current, newQi, n)) {
                    char[] c = new char[n];
                    Arrays.fill(c, '.');
                    c[newQi] = 'Q';
                    current.add(new String(c));
                    col[newQi] = true;
                    putQ(r, current, n, col);
                    current.remove(current.size() - 1);
                    col[newQi] = false;
                }
            }
        }
    }

    boolean checkQ(List<String> c, int newQi, int n) {
        for (int i = c.size() - 1; i >= 0; i--) {
            int upDiff = c.size() - i;
            int leftUpCornerCheck = newQi - upDiff;
            if (leftUpCornerCheck >= 0 && c.get(i).charAt(leftUpCornerCheck) == 'Q') return false;
            int rightUpCornerCheck = newQi + upDiff;
            if (rightUpCornerCheck < n && c.get(i).charAt(rightUpCornerCheck) == 'Q') return false;
        }
        return true;
    }
}
