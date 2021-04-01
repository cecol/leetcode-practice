package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC454_4SumII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC454_4SumII();
        LC.fourSumCount(
                new int[]{0},
                new int[]{0},
                new int[]{0},
                new int[]{0}
        );
    }

    /**
     * 我寫的O(N^3) 是沒有過的, TLE -> 我沒有理解為什麼可以縮成 O(N^2)
     * https://leetcode.com/problems/4sum-ii/discuss/93973/Dividing-arrays-into-two-parts.-Full-thinking-process-from-naive(N4)-to-effective(N2)-solution
     * 這邊有講
     * 最直覺的 O(N^4) 直接 nest loop all possible A,B,C,D for  (A[i] + B[j] + C[k] + d[h]) == 0. Time complexity: N^4
     * 簡化一點, Ｄ最內層直接用Map解掉 O(N^3): A[i] + B[j] + C[k] + d == 0 ----> d = -A[i] - B[j] - C[k]
     * 但我花了點時間才理解掉其實可以
     * A[i] + B[j] == - C[k] - D[h]
     * A[i] + B[j] == - (C[k]+D[h])
     * 先算出 nest loop A,B all sum, 再把 A,B 計數到的all sum 去 nest loop C,D -> 確實也是這四種的排列組合
     * 主要原因就是加法有交換性
     * We aim to find all possible A[i] + B[j] + C[k] + D[l] = 0, that is,
     * A[i] + B[j] = -(C[k] + D[l])
     * loop A[i] + B[j]
     * then loop -(C[k] + D[l])
     * 我一直有錯覺 覺得拆成兩個 nest loop 就破壞了 4 nest loop的組合
     * 但事實上並沒有, 他只是先把 caching A,B combination in Map rather than get A,B in extra two outer loops
     * 兩著雖然時間複雜度天差地遠, 但語意是依樣的, 重點在於 Map就像種特殊催化劑, 達成跟4 nest loop 依樣效果
     * 而且答案要的是 tuple of (i, j, k, l) 他是 offset的組合, 因為offset的組合不同於值的組合
     * offset組合不會有重複, 但是值的組合會因為重複或者次序所以有重複case -> 因此跟 3SUM的算法又不一樣
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (A.length == 0) return 0;
        Map<Integer, Integer> mab = new HashMap<>();
        for (int a : A)
            for (int b : B)
                mab.put(a + b, mab.getOrDefault(a + b, 0) + 1);
        int s = 0;
        for (int c : C)
            for (int d : D)
                if (mab.containsKey(-c - d)) s += mab.get(-c - d);
        return s;
    }
}
