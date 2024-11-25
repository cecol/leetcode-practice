package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

import java.util.*;

public class LC127WordLadder extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 只記得是 BFS + 單字切換, 但忘記單字切換細節
    // 1. 用 set 存 單字候選人,
    // 2. bfs 下去每個單字 字元下去換, 換到 set 就可以加入 bfs,
    // 3. bfs 中途找到終點就可以回傳
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        Set<String> st = new HashSet<>(wordList);
        Queue<String> q = new LinkedList<>();
        int l = 0;
        q.add(beginWord);
        while (!q.isEmpty()) {
            int qs = q.size();
            for (int i = 0; i < qs; i++) {
                String w = q.poll();
                if (w.equals(endWord)) return l + 1;
                char[] wl = w.toCharArray();
                for (int j = 0; j < w.length(); j++) {
                    char old = wl[j];
                    for (int c = 0; c < 26; c++) {
                        char cc = (char) ('a' + c);
                        if (old == cc) continue;
                        wl[j] = cc;
                        String wll = String.valueOf(wl);
                        if (st.remove(wll)) q.add(wll);
                    }
                    wl[j] = old;
                }
            }
            l++;
        }
        return 0;
    }
}
