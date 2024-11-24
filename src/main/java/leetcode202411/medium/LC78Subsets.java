package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;

public class LC78Subsets extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 雖然是知道 dfs + backtrace, 但記錯了, dfs 內不用 loop, 只要針對當下的 idx 處置就好
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        dfs(res, nums, new ArrayList<>(), 0);
        return res;
    }

    void dfs(List<List<Integer>> res, int[] nums, List<Integer> cur, int idx) {
        if(idx == nums.length) return;
        cur.add(nums[idx]);
        res.add(new ArrayList<>(cur));
        dfs(res,nums,cur,idx+1);
        cur.remove(cur.size()-1);
        dfs(res,nums,cur,idx+1);
    }

}
