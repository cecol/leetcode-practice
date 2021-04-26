package leetcode202104.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC1128NumberOfEquivalentDominoPairs extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1128NumberOfEquivalentDominoPairs();
    }

    /**
     * 自己有解出來, 但真的超慢
     * https://leetcode.com/problems/number-of-equivalent-domino-pairs/discuss/340458/Java-one-pass-O(1)-space-beats-100
     * 看了人家的正解覺得自己原本的解法真的是想太多了
     * 1. pair 的encode 就直接 d[0]*10 + d[1]
     * 2. 只有10*10種組合, 所以只有 in[100] 去記錄所有可能
     * 3. 因為是從頭到尾走過去, 所以走到當前就可以加總當前
     * -> [1,2] encode == 12, [2,1] encode == 21, 但這兩者算成一樣的, 所以都統一encode成12
     * -> 然後直接count[12]++ 就好 -> res 也直接加總當前的count[12]結果就好 -> 因為當前的加總就是把前面比較小的offset的結算
     * -> 再到後面遇到會在加總一次是因為 遇到更大的offset, 所以前面的要再多算一次 -> 最關鍵也很不直覺可以看出來的要點
     * -> 所以看起來如果是找 i < j pattern 的count數 都是這樣運作
     *
     * 其實這邊有比較仔細的推演跟解釋
     * https://leetcode.com/problems/number-of-equivalent-domino-pairs/discuss/549709/Easy-Without-HashMap-Java-Solution-Runtime-0ms
     * https://leetcode.com/problems/number-of-equivalent-domino-pairs/discuss/340022/JavaC%2B%2BPython-Easy-and-Concise
     * 某種程度就是 guass formula 推演
     * 比如說組成的count是 1,3,7,11 -> 合法組成只有{[1,3], [1,7], [1,11]}, {[3,7], [3,11]}, {[7,11]} -> 就是 3+2+1 ,
     * 要馬就是用 guass formula 去推算 每一個count res += v * (v - 1) / 2;
     * 不然就是每次在 count[x]++ 前就加進 res, 都是一樣道理
     */
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] count = new int[100];
        int res = 0;
        for (int[] d : dominoes) {
            int up = d[0] < d[1] ? d[0] : d[1];
            int down = d[0] < d[1] ? d[1] : d[0];
            res += count[up * 10 + down]++;
        }
        return res;
    }


    public int numEquivDominoPairsSlow(int[][] dominoes) {
        int n = dominoes.length;
        Map<String, List<Integer>> dm = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String pk = dominoes[i][0] + "," + dominoes[i][1];
            if (!dm.containsKey(pk)) dm.put(pk, new ArrayList<>());
            dm.get(pk).add(i);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            String dk = dominoes[i][0] + "," + dominoes[i][1];
            String pk = dominoes[i][1] + "," + dominoes[i][0];
            if (dm.containsKey(dk)) {
                List<Integer> allPair = dm.get(dk);
                int idx = Collections.binarySearch(allPair, i);
                res += (allPair.size() - idx - 1);
            }
            if (dominoes[i][0] != dominoes[i][1] && dm.containsKey(pk)) {
                List<Integer> allPair = dm.get(pk);
                int idx = -Collections.binarySearch(allPair, i) - 1;
                res += Math.max(0, allPair.size() - idx);
            }
        }
        return res;
    }
}
