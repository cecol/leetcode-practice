package leetcode202104.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC140WordBreakII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC140WordBreakII();
//        LC.wordBreak("catsanddog", List.of("cat", "cats", "and", "sand", "dog"));
        LC.wordBreak("pineapplepenapple", List.of("apple","pen","applepen","pine","pineapple"));
    }

    /**
     * 很直觀地用了backtracking解了 0 ms, faster than 100.00%
     * 中間卡了一下, 看錯以為word不可重複使用QQ
     * */
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (s.length() == 0) return res;
        boolean[] v = new boolean[wordDict.size()];
        dfs(res, sb, 0, wordDict, s);
        return res;
    }

    private void dfs(List<String> res, StringBuilder sb, int idx, List<String> wordDict, String s) {
        if (idx == s.length()) {
            log.debug(sb.toString());
            res.add(sb.toString().trim());
        } else {
            for (int i = 0; i < wordDict.size(); i++) {
                String ws = wordDict.get(i);
                if (s.startsWith(ws, idx)) {
                    sb.append(ws).append(' ');
                    dfs(res, sb, idx + ws.length(), wordDict, s);
                    sb.delete(sb.length() - ws.length() - 1, sb.length());
                }
            }
        }
    }
}
