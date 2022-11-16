package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LC753CrackingTheSafe extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC753CrackingTheSafe();
    }

    /**
     * https://halfrost.com/go_s2_de_bruijn/
     * 這題是找出 德布鲁因序列 De Bruijn sequence
     * <p>
     * 德布鲁因序列(De Bruijn sequence)，记为B(k, n)，
     * 是 k 元素构成的循环序列。所有长度为 n 的 k 元素构成序列都在它的子序列（以环状形式）中，出现并且仅出现一次。
     * <p>
     * 例如，序列 00010111 属于B(2,3)。 00010111 的所有长度为3的子序列为
     * 000,001,010,101,011,111,110,100，正好构成了 {0,1} ^3 的所有组合。
     * <p>
     * https://lanstonchu.wordpress.com/2018/08/28/de-bruijn-sequence-and-graph-theory/
     *
     * 解法很直觀, 但前提是你要知道 De Bruijn sequence 存在 且相信這麼做一定會找到
     * 假設 n = 3, k = 10{0..9} 從字串 000 開始 DFS
     * 總共有 k^n 可能性, 所以當 HashSet 收集到的不同字串長度 == k^n, 那就找完了
     * 沒有的話, 取當前找到的 n- 1 字串 下去配上 {0..9} 可能性再去DFS遞迴 , DFS遞迴有回傳 true 就是答案
     * 沒有的話, 就代表當前 {0..9} 選的不是正確, 再試下一個  試到沒有就回傳 false
     * 這麼做一定會找的答案一定剛好是 De Bruijn sequence
     */
    public String crackSafe(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int total = (int) (Math.pow(k, n));
        for (int i = 0; i < n; i++) sb.append('0');
        Set<String> visited = new HashSet<>();
        visited.add(sb.toString());

        dfs(sb, total, visited, n, k);
        return sb.toString();
    }

    boolean dfs(StringBuilder sb, int goal, Set<String> visited, int n, int k) {
        if (visited.size() == goal) return true;
        String prev = sb.substring(sb.length() - n + 1);
        for (int i = 0; i < k; i++) {
            String next = prev + i;
            if (!visited.contains(next)) {
                visited.add(next);
                sb.append(i);
                if (dfs(sb, goal, visited, n, k)) return true;
                else {
                    visited.remove(next);
                    sb.delete(sb.length() - 1, sb.length());
                }
            }
        }
        return false;
    }
}
