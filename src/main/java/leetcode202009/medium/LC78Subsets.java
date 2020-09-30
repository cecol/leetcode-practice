package leetcode202009.medium;

import java.util.*;
import java.util.stream.Collectors;

public class LC78Subsets {
    public static void main(String[] args) {
        var r = subsets3(new int[]{1, 2, 3});
        for (List<Integer> i : r) System.out.println(i.stream().map(Object::toString).collect(Collectors.joining(",")));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> r = new ArrayList<>();
        List<Integer> x = new ArrayList<>();
        for (int i : nums) x.add(i);
        getSet(x, new HashSet<Integer>(), r);
        return r;
    }

    public static void getSet(List<Integer> x, Set<Integer> a, List<List<Integer>> r) {
        if (x.isEmpty()) {
            r.add(new ArrayList<>(a));
        } else {
            Integer g = x.remove(0);
            getSet(new ArrayList<>(x), new HashSet<>(a), r);
            a.add(g);
            getSet(new ArrayList<>(x), new HashSet<>(a), r);
        }
    }

    public static List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> r = new ArrayList<>();
        List<Integer> x = new ArrayList<>();
        r.add(new ArrayList<>());
        getSet2(nums, 0, x, r);
        return r;
    }

    /**
     * recursive case
     * */
    public static void getSet2(int[] nums, int offset, List<Integer> x, List<List<Integer>> r) {
        if (offset == nums.length) {
            return;
        } else {
            x.add(nums[offset]);
            r.add(new ArrayList<>(x));
            getSet2(nums, offset + 1, x, r);
            x.remove(x.size() - 1);
            getSet2(nums, offset + 1, x, r);
        }
    }

    /**
     * iteration case
     * */
    public static List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> r = new ArrayList<>();
        r.add(new ArrayList<>());
        for (int i : nums) {
            List<List<Integer>> n = new ArrayList<>();
            for (List<Integer> l : r) {
                List<Integer> cp = new ArrayList<>(l);
                cp.add(i);
                n.add(cp);
            }
            r.addAll(n);
        }
        return r;
    }
}
