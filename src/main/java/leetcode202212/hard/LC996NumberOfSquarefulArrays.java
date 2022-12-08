package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class LC996NumberOfSquarefulArrays extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC996NumberOfSquarefulArrays();
    }

    /**
     * https://leetcode.com/problems/number-of-squareful-arrays/discuss/238593/Java-DFS-%2B-Unique-Perms
     * 這題也是 NP 問題
     * 看給的數據範圍 1 <= nums.length <= 12 就知道了
     * 但有些細節要注意
     * 1. 如何知道是 perfect square?
     * - (sqr - Math.floor(sqr)) == 0; 就可以確定 - 這邊卡住
     * 2. 用一個 boolean[] v 記載用過了沒
     * 3. int[] nums 要先 sort 過來處理 重複數字問題
     * */
    int res = 0;

    public int numSquarefulPerms(int[] nums) {
        int n = nums.length;
        boolean[] v = new boolean[n];
        Arrays.sort(nums);
        dfs(new ArrayList<>(), nums, v, -1);
        return res;
    }

    void dfs(List<Integer> cur, int[] nums, boolean[] v, int lastNumber) {
        if (cur.size() == nums.length) {
            res++;
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (v[i] || (i > 0 && nums[i] == nums[i - 1] && !v[i - 1])) continue;
            if (lastNumber != -1) {
                if (!isSquare(nums[i], lastNumber)) continue;
            }
            v[i] = true;
            cur.add(nums[i]);
            dfs(cur, nums, v, nums[i]);
            cur.remove(cur.size() - 1);
            v[i] = false;
        }
    }

    boolean isSquare(int x, int y) {
        double sqr = Math.sqrt(x + y);
        return (sqr - Math.floor(sqr)) == 0;
    }
}
