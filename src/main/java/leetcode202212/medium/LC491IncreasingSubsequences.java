package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC491IncreasingSubsequences extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC491IncreasingSubsequences();
    }

    /**
     * 原本以為很簡單, 想說用 List<List<Integer>> res 來下去做
     * 類似 LC90SubsetsII, 用同樣辦法去重  但發現還是有 case 過不了
     * 知道用 Set 可以去重 但我不知道 Set 可以直接去重 List<Integer>
     * https://leetcode.com/problems/increasing-subsequences/discuss/?currentPage=1&orderBy=hot&query=
     * 是不是這類問題以後要去重都用 Set<List<Integer>> 就好??
     * 而且速度也不慢...
     * */
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        dfs(res, new ArrayList<>(), nums, 0);
        return new ArrayList<>(res);
    }

    void dfs(Set<List<Integer>> res, List<Integer> cur, int[] nums, int idx) {
        if (cur.size() > 1) res.add(new ArrayList<>(cur));
        for (int i = idx; i < nums.length; i++) {
            if ((cur.size() == 0 || nums[i] >= cur.get(cur.size() - 1))) {
                cur.add(nums[i]);
                dfs(res, cur, nums, i + 1);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
