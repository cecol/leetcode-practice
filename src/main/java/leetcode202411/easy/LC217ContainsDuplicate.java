package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC217ContainsDuplicate extends BasicTemplate {
    public static void main(String[] args) {
    }


    // Set or map 秒解
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> ss = new HashSet<>();
        for (int n : nums) if (!ss.add(n)) return true;
        return false;
    }

}
