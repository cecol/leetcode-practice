package leetcode202105.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class LC1460MakeTwoArraysEqualByReversingSubArrays extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1460MakeTwoArraysEqualByReversingSubArrays();
    }

    /**
     * 既然能夠 任意左右交換, 就是 bubble sort概念
     * 所以檢查是否元素都相等, 是的話一定可以任意交換達成目標
     * 用 Map 解 Runtime: 6 ms, faster than 20.46%
     * */
    public boolean canBeEqual(int[] target, int[] arr) {
        Map<Integer, Integer> tc = new HashMap<>();
        for(int i: target) tc.put(i, tc.getOrDefault(i,0)+1);
        for(int i: arr) tc.put(i, tc.getOrDefault(i,0)-1);
        for(int i: tc.values()) if(i != 0) return false;
        return true;
    }
}
