package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class LC127WordLadder extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC127WordLadder();
    }

    /**
     * https://leetcode.com/problems/word-ladder/discuss/40728/Simple-Java-BFS-solution-with-explanation
     * 有初步想法, 但是沒有解出來, 很直觀的就是
     * 1. build a graph whose nodes represent strings and edges connect strings that are only 1 character apart
     * 2. and then we apply BFS from the startWord node. If we find the endWord, we return the level count of the bfs.
     * 我也是這樣想, 但沒有寫好, 後來這篇才有說明其實有很多細節可以優化
     * 1. 簡化 build adjacency graph -> 不用建立 graph, 直接拿當前字串一個一個改字元來找Set中有無該下一個字串
     * -> When we build adjacency list graph, we don't use two loops to check every pair of string to see if they are 1 character apart.
     * 2. 拜訪過的字串就從 Set中刪除, 也不用記載誰拜訪過了
     * 這題有幾個思路我要記好
     * 1. BFS的Queue轉換, 不必新舊Queue(不用建新Queue來改ref)切換, 只要一個Queue就好
     * -> 只要每一層BFS過層中記好當前Queue size, 用for loop poll()該size就好, 反正後面加的, 也是下一輪才會遇到
     * 2. 從 word1 到下一個word2, 不是透過建立的 graph, 建graph太複雜了,
     * -> 直接當前BFS走到的String, 一個字元去改, 看看可以走的有無包含改過的就是
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        Set<String> st = new HashSet<>(wordList);
        Queue<String> q = new LinkedList<>();
        int l = 0;
        q.add(beginWord);
        while (q.size() > 0) {
            int qs = q.size();
            for (int i = 0; i < qs; i++) {
                String w = q.poll();
                if (w.equals(endWord)) return l + 1;
                char[] wl = w.toCharArray();
                for (int j = 0; j < w.length(); j++) {
                    char old = wl[j];
                    for (int c = 0; c < 26; c++) {
                        char cc = (char) ('a' + c);
                        if(old == cc) continue;
                        wl[j] = cc;
                        String wll = String.valueOf(wl);
                        if(st.remove(wll)) q.add(wll);
                    }
                    wl[j] = old;
                }
            }
            l++;
        }
        return 0;
    }

}
