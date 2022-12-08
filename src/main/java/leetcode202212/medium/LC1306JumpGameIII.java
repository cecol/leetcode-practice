package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LC1306JumpGameIII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1306JumpGameIII();
    }

    /**
     * 很直觀就是下去左右跳, 帶一個 visited set 檢查就好
     * */
    public boolean canReach(int[] arr, int start) {
        if (arr[start] == 0) return true;
        Set<Integer> v = new HashSet<>();
        boolean res ;
        res = dfs(arr, start - arr[start], v);
        if (res) return true;
        return dfs(arr, start + arr[start], v);
    }

    boolean dfs(int[] arr, int start, Set<Integer> v) {
        if(start < 0 || start >= arr.length) return false;
        if(v.contains(start)) return false;
        if(arr[start] == 0) return true;
        v.add(start);
        boolean res ;
        res = dfs(arr, start - arr[start], v);
        if (res) return true;
        return dfs(arr, start + arr[start], v);
    }
}
