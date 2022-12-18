package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC332ReconstructItinerary extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC332ReconstructItinerary();
    }

    /**
     * 沒有看出這題是有個概念 之前不知道
     * Eulerian path/trail -> a path that visits each edge exactly once(但是每個點可以拜訪N次)
     * Eulerian cycle -> Eulerian trail that starts and ends on the same vertex.(但是每個點可以拜訪N次)
     * -> 這個因為保證可以回到原點 -> 保證 even degree -> 一進一出 才有機會回到該點
     * -> A connected graph has an Euler cycle if and only if every vertex has even degree.
     * Hamiltonian -> visited each node once and only once
     * 然後有個相對應的 Hierholzer's algorithm -> 找 Euler cycles
     * (for Eulerian path problems, one should consider Fleury's algorithm)
     * -> 所以假設一定有 even degree
     * -> 1.先從v 開始走, 走其他 edge 直到回到 v, 一定可以回來, 因為保證 even degree -> 一條出去就一條進來
     * -> 2 走到當前點u 都再去走還沒走過的點, 直到走回u (v延伸到u 的tour, 然後繼續延伸下去, 直到回來u回來v)
     * -> 3.因為是聯通圖, 所以重複上述一定有結果
     *
     * 1. 我一開始沒有想到上面相關的理論, 但我有想到說要建立  Map<String, PriorityQueue<String>> m 來記錄飛航的adjacent
     * -> PriorityQueue<String> 是很直觀的要先挑最小目的地, 但是我沒想通的是(討論中也很多人有提出這個問題)
     * -> 因為PriorityQueue<String>挑出來的下一個是最小排序目的地沒錯, 但是該目的地可能不會有結果所以 dfs回來 backtrack
     * -> 我一直以為dfs要加回去PriorityQueue<String>, 想說可能之後要用
     * -> how do you make sure there is no dead end since you always choose the "smallest" arrivals (min heap)
     * 因為是Eulerian path/trail的關鍵
     * 1. Starting at the first node, we can only get stuck at the ending point,
     * -> since every node except for the first and the last node has even number of edges,
     * -> when we enter a node we can always get out
     * 就是說除了起點和終點, 其他點都是 even degree, 確保進來就可以出去
     * 有向圖
     * 1. 起點 outDegree - inDegree == 1
     * 2. 終點 inDegree - outDegree == 1
     * 3. 其他點 inDegree - outDegree == 0
     * 無向圖
     * 1. 起點終點 degree = odd
     * 2.其他點 degree == even
     * (Eulerian cycle是所有點都是 even degree, Eulerian path/trail是只有起點終點以外)
     * -> we are at the destination and if all edges are visited, we are done
     * -> 所以只有終點先dfs結束 -> 然後遞迴回到前一點
     * 2. 還有個細節是在DFS中
     * 當前的點不會先加入, 而是等他的 adj 全都走完才 addFirst
     * 這是因為只有走到終點(最先沒有adj), 才能確立Eulerian path 長怎樣, 走到終點才開始建構 path list
     * 所以從終點尾巴dfs遞迴開始回退建立回去, 就是說對每個點dfs來說, 當他dfs中的所有 adj走完了, 才會把該點加入

     * 如果一進DFS就把該點加入
     * 1. 第一點當然就可以先加入, 看起來也是對的, 但如果下一點就是終點, 終點也就直接加入了 path -> 邏輯一定錯誤
     * -> 所以反思 -> 只有當真正走完才加入該點 -> 而終點點會最先走完的 所以終點才會最早加入 後續的點也是依序加入
     * -> 因為是走完才加入 如果用 add(u) -> 結果會是反序 -> 所以用addFirst 才對
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        LinkedList<String> res = new LinkedList<>();
        if (tickets.size() == 0) return res;
        Map<String, PriorityQueue<String>> m = new HashMap();
        for (List<String> t : tickets) {
            m.computeIfAbsent(t.get(0), i -> new PriorityQueue<String>());
            m.get(t.get(0)).add(t.get(1));
        }
        dfs(res, m, "JFK");
        return res;
    }

    private void dfs(LinkedList<String> res, Map<String, PriorityQueue<String>> m, String s) {
        PriorityQueue<String> q = m.get(s);
        while (q != null && q.size() > 0) dfs(res, m, q.poll());
        res.addFirst(s);
    }


}
