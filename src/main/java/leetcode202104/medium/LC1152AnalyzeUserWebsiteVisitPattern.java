package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.math.Ordering;

import java.util.*;

public class LC1152AnalyzeUserWebsiteVisitPattern extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1152AnalyzeUserWebsiteVisitPattern();
    }

    /**
     * 這題很冗, 題意也不清楚, 不過我猜可能是在考真實情境, 不會總是有明確的演算法解&說明清楚
     * https://leetcode.com/problems/analyze-user-website-visit-pattern/discuss/550403/Java-Solution%3A-With-Comments-95-faster
     * 挑了個比較解釋明確的
     * 1. 要挑出 each sequence of 3 web sites visited by one user
     * -> excluding the duplicate sequence -> 因為同個人可能在不同時間看同一組website
     * -> 所以user 沒看到 3個網站的都可以省了 -> 因為他組不出來 3 sequence 來計算解答
     * 2. 先把 username timestamp website group 起來成一個class 並且攤平 sort
     * 3. 搜集每個user 看過的 webs in Map<String, List<String>> userWeb -> 因為前面攤平 sort, 這邊加入的 List<String> 都是由早到晚看的web
     * 4. 根據Map<String, List<String>> userWeb gen每個user出所有可能的 3 sequences 並放到set中: Set<List<String>> seq3Set
     * 5. 然後把Set<List<String>> seq3Set 加入到 Map<List<String>, Integer> seq3UserCount
     * 6. 最後loop seq3UserCount找出max users visit -> 如果有一樣的就是整個seq轉成字串比對
     *
     * 所以有幾個重點 user 看過 a,b,c -> 組出來的 sequences 3 可能有 a,b,c or b,a,c 各種排列組合
     * -> 因為不同時間看的所產生不同排列組合 -> 所以這些都要記下來且count by users -> 最後可能就是由字串比對lexicographically 來決勝負
     */
    class Visit {
        String n;
        String w;
        int t;

        public Visit(String name, String web, int ts) {
            n = name;
            w = web;
            t = ts;
        }
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<Visit> vs = new ArrayList<>();
        for (int i = 0; i < username.length; i++) {
            vs.add(new Visit(username[i], website[i], timestamp[i]));
        }
        Collections.sort(vs, (x, y) -> (x.t - y.t));

        Map<String, List<String>> userWeb = new HashMap<>();
        for (Visit v : vs) {
            userWeb.computeIfAbsent(v.n, i -> new ArrayList<>());
            userWeb.get(v.n).add(v.w);
        }

        Map<List<String>, Integer> seq3UserCount = new HashMap<>();
        for (List<String> webs : userWeb.values()) {
            if (webs.size() < 3) continue;
            Set<List<String>> seq3Set = gen3Seq(webs);
            for (List<String> seq3 : seq3Set) {
                seq3UserCount.put(seq3, seq3UserCount.getOrDefault(seq3, 0) + 1);
            }
        }

        List<String> res = new ArrayList<>();
        int max = 0;
        for(Map.Entry<List<String>, Integer> kv:seq3UserCount.entrySet()) {
            if(kv.getValue() > max) {
                max = kv.getValue();
                res = kv.getKey();
            } else if(kv.getValue() == max) {
                if(kv.getKey().toString().compareTo(res.toString()) < 0) res = kv.getKey();
            }
        }
        return res;
    }

    private Set<List<String>> gen3Seq(List<String> webs) {
        Set<List<String>> seqSet = new HashSet<>();
        for (int i = 0; i < webs.size(); i++) {
            for (int j = i + 1; j < webs.size(); j++) {
                for (int k = j + 1; k < webs.size(); k++) {
                    List<String> ls = new ArrayList<>();
                    ls.add(webs.get(i));
                    ls.add(webs.get(j));
                    ls.add(webs.get(k));
                    seqSet.add(ls);
                }
            }
        }
        return seqSet;
    }
}
