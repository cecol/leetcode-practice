package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC39CombinationSum extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 有想起基礎架構, 但還是忘記細節
    // 1. dfs 下去, 用 remain 去看剩多少, == 0 就是找到, < 0 代表超過 要 return
    // 2. 要帶每一層 dfs idx 下去才可以避免重複
    // 3. 找到的時候可以 res.add(new ArrayList<>(cur)); 複製一個新的
    // 4. dfs 回來要 remove 上一步
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(res, new ArrayList<>(), target, 0, candidates);
        return res;
    }

    void dfs(List<List<Integer>> res, List<Integer> cur, int remain, int idx, int[] candidates) {
        if (remain == 0) {
            res.add(new ArrayList<>(cur));
        } else if (remain < 0) return;

        for (int i = idx; i < candidates.length; i++) {
            cur.add(candidates[i]);
            dfs(res, cur, remain - candidates[i], i, candidates);
            cur.remove(cur.size() - 1);
        }
    }
}
