package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC425WordSquares extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC425WordSquares();
    }

    /**
     * https://leetcode.com/problems/word-squares/discuss/91333/Explained.-My-Java-solution-using-Trie-126ms-1616
     * 這題有個關鍵規則是
     *     1 2  3  4 5
     * 1.  a b [c] d e
     * 2.  b c [f] g h
     * 3. [c f] x x x
     * 在找第三個時候 他是前兩個的 charAt(3) 的 prefix
     *
     * 然後, 當選擇 第一個時候, 就已經知道, 後面要選幾個
     * 所以就帶這樣資訊下去 DFS
     * 1. 建立所有 prefix map, 這樣要找符合前 i-1 row prefix 會快很多, 不然會 TLE
     * 2. 每個字下去 DFS, 當選擇第一個字時候, DFS 深度就決定了, 有找到期望深度就是答案可以回傳
     * - prefix map 沒找到 代表根本組合不出來
     * - 當前 DFS 就是拉出之前找到的字串 組出prefix 去 prefix map 找下一個
     *
     * */
    public List<List<String>> wordSquares(String[] words) {
        Map<String, Set<String>> map = new HashMap<>();
        for (String w : words) {
            for (int i = 1; i <= w.length(); i++) {
                String prefix = w.substring(0, i);
                if (!map.containsKey(prefix)) map.put(prefix, new HashSet<>());
                map.get(prefix).add(w);
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (String w : words) {
            List<String> cur = new ArrayList<>();
            cur.add(w);
            dfs(res, cur, map, w.length());
        }
        return res;
    }

    void dfs(List<List<String>> res, List<String> cur, Map<String, Set<String>> prefix, int len) {
        if (cur.size() == len) {
            res.add(new ArrayList<>(cur));
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String candidate : cur) sb.append(candidate.charAt(cur.size()));
        if (!prefix.containsKey(sb.toString())) return;
        for (String next : prefix.get(sb.toString())) {
            cur.add(next);
            dfs(res, cur, prefix, len);
            cur.remove(cur.size()-1);
        }
    }
}
