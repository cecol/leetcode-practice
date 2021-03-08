package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class LC217ContainsDuplicate extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC217ContainsDuplicate();
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int n : nums) if (!s.add(n)) return true;
        return false;
    }
}
