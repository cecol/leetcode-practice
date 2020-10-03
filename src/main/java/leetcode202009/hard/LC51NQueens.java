package leetcode202009.hard;

import leetcode202009.BasicTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LC51NQueens extends BasicTemplate {

    public static void main(String[] args) {
        var LC = new LC51NQueens();
        var r = LC.solveNQueens2(4);
        for (List<String> s : r) {
            System.out.println(String.join("\n", s));
            System.out.println("----------------------------");
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> r = new ArrayList<>();
        boolean[] col = new boolean[n];
        Arrays.fill(col, false);
        putQ(r, new ArrayList<>(), n, col);
        return r;
    }

    public void putQ(List<List<String>> r, List<String> current, int n, boolean[] col) {
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

    public boolean checkQ(List<String> c, int newQi, int n) {
        for (int i = c.size() - 1; i >= 0; i--) {
            int leftCheck = newQi - (c.size() - i);
            if (leftCheck >= 0 && c.get(i).charAt(leftCheck) == 'Q') return false;
            int rightCheck = newQi + (c.size() - i);
            if (rightCheck < n && c.get(i).charAt(rightCheck) == 'Q') return false;
        }
        return true;
    }

    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> r = new ArrayList<>();
        int[][] attack = new int[n][n];
        putQByAttack(0, n, r, new ArrayList<>(), attack);
        return r;
    }

    public void putQByAttack(int d, int n, List<List<String>> r, List<Integer> putQ, int[][] attack) {
        /**
         * if(){ return } is faster than
         *  if(){ no return} else {}
         * */
        if (d == n) {
            // StringBuilder is faster to build a string
            StringBuilder sb = new StringBuilder();
            List<String> q = new ArrayList<>();
            for (int i = 0; i < n; i++) sb.append(".");
            /**
             * for (int i = 0; i < putQ.size(); i++) is faster than
             *  for (Integer i : putQ)
             *  a lot
             * */
            for (int i = 0; i < putQ.size(); i++) {
                Integer col = putQ.get(i);
                sb.setCharAt(col, 'Q');
                q.add(sb.toString());
                sb.setCharAt(col, '.');
            }
            r.add(q);
            return;
        }
        for (int newQi = 0; newQi < n; newQi++) {
            if (attack[d][newQi] == 0) {
                updateAttack(d, newQi, n, attack);
                putQ.add(newQi);
                putQByAttack(d + 1, n, r, putQ, attack);
                putQ.remove(putQ.size() - 1);
                restoreAttack(d, newQi, n, attack);
            }
        }
    }

    private void updateAttack(int downCheck, int newQi, int n, int[][] attack) {
        // update all below/hill/dale positions by +1
        for (int k = downCheck + 1, offset = 1; k < n; ++k, ++offset) {
            attack[k][newQi] += 1; // mid
            if (newQi - offset >= 0) attack[k][newQi - offset] += 1; // left
            if (newQi + offset < n) attack[k][newQi + offset] += 1; // right
        }
    }

    private void restoreAttack(int downCheck, int newQi, int n, int[][] attack) {
        // restore all below/hill/dale positions by -1
        for (int k = downCheck + 1, offset = 1; k < n; ++k, ++offset) {
            attack[k][newQi] -= 1;
            if (newQi - offset >= 0) attack[k][newQi - offset] -= 1; // left
            if (newQi + offset < n) attack[k][newQi + offset] -= 1; // right
        }
    }
}
