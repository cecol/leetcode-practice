package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC126WordLadderII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC126WordLadderII();
        LC.findLadders("hit", "cog", List.of("hot","dot","dog","lot","log","cog"));
    }


    /**
     * https://leetcode.com/problems/word-ladder-ii/solutions/40457/my-ac-java-solution-using-dfs-bfs/
     * 看起來跟 LC127WordLadder 一模一樣  但要完整建立出路徑實在是考得很全面
     * 1. 還是雷同 LC127WordLadder bfs 手法, 一個 char 一個 char 下去換 下去找 下去 dfs
     * 關鍵是建立 Map<String, Integer> path = new HashMap<>(); 記載每個看過字元的 path idx
     * Ex: "hit" -> "hot" -> "dot", 就會是 "hit": 0, "hot": 1, "dot": 2 這樣
     * 2. dfs, 但這邊要是從 endWord 走回 beginWord,
     * 我一直卡在以為是 beginWord 走向 endWord, 這會 TLE!! 因為 beginWord 會多走很多死胡同！！
     * 只有 endWord 走回去才是對的
     * 走的過程就是
     * 1. 看看 path 有沒有當前 curWord
     * 2. 看看 curWord 的 step, 期待下一個 word step 是 path.get(curWord) - 1
     * 3. curWord 依然一個一個 char 下去換找 newWord, if(path.contains(newWord) && path.get(newWord) == path.get(curWord) - 1)
     * - next level dfs
     * */
    Map<String, Integer> path = new HashMap<>();
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wl = new HashSet<>(wordList);
        if (!wl.contains(endWord)) return res;
        bfs(beginWord, endWord, wl);
        dfs(beginWord, endWord, new ArrayList<>());
        return res;
    }

    void dfs(String curWord, String endWord, List<String> curPath) {
        if (curWord.equals(endWord)) {
            curPath.add(curWord);
            Collections.reverse(curPath);
            res.add(curPath);
            return;
        }
        if (!path.containsKey(curWord)) return;
        curPath.add(curWord);
        int nextDepth = path.get(curWord) - 1;
        for (int i = 0; i < curWord.length(); i++) {
            char[] ca = curWord.toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
                if (ca[i] == c) continue;
                ca[i] = c;
                String newWord = new String(ca);
                if (path.containsKey(newWord) && path.get(newWord) == nextDepth) {
                    dfs(newWord, endWord, new ArrayList<>(curPath));
                }
            }
        }
    }

    void bfs(String beginWord, String endWord, Set<String> set) {
        Queue<String> bfs = new LinkedList<>();
        bfs.offer(beginWord);
        path.put(beginWord, 0);
        while (bfs.size() > 0) {
            String current = bfs.poll();
            if (current.equals(endWord)) continue;
            for (int i = 0; i < current.length(); i++) {
                char[] ca = current.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    if (ca[i] == c) continue;
                    ca[i] = c;
                    String newWord = new String(ca);
                    if (set.contains(newWord) || newWord.equals(endWord)) {
                        if (!path.containsKey(newWord)) {
                            path.put(newWord, path.get(current) + 1);
                            bfs.offer(newWord);
                        }
                    }
                }
            }
        }
    }
}
