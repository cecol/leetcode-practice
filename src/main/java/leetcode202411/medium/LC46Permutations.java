package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC46Permutations extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 因為剛解過 LC39 CombinationSum
    // 這題秒解
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, nums, new ArrayList<>());
        return res;
    }

    void dfs(List<List<Integer>> res, int[] nums, List<Integer> cur) {
        if (cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int n : nums) {
            if(cur.contains(n)) continue;
            cur.add(n);
            dfs(res, nums, cur);
            cur.remove(cur.size()-1);
        }
    }
}
