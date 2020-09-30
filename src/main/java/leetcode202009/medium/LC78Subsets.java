package leetcode202009.medium;

import java.util.*;
import java.util.stream.Collectors;

public class LC78Subsets {
    public static void main(String[] args) {
        var r = subsets(new int[]{1, 2, 3});
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
}
