package util;

import leetcode202210.contest.BiweeklyContest90;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Template {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        List<int[]> res = new ArrayList<>();
        res.toArray(new int[res.size()][2]);
        Collections.reverse(res);
        int[][] intervals = new int[][]{{1, 2}};
        Arrays.sort(intervals, (x, y) -> x[0] - y[0]);
        Arrays.binarySearch(new int[2], 1);
        Arrays.copyOf(new int[]{}, 1);


        Map<String, TreeMap<Integer, String>> m = new TreeMap<>();
        TreeMap<Integer, String> tm = new TreeMap<>();

        String s = "";
        Deque<TreeNode> bfs = new LinkedList<>();
        String.valueOf(1);

        Stack<Integer> monotonic = new Stack<>();

        PriorityQueue<Integer> p1 = new PriorityQueue<>((x,y)->-x-(-y));

        StringBuilder sb = new StringBuilder();

        Set<Integer> set = new HashSet<>();

        new ArrayList<>(1);
    }
}
