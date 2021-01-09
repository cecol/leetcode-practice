package leetcode2021.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;

public class LC841KeysAndRooms extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC841KeysAndRooms();
        var s = LC.canVisitAllRooms(List.of(List.of(1), List.of(2), List.of(3), new ArrayList<Integer>()));
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] v = new boolean[n];
        dfs(v, 0, rooms);
        log.debug("{}", v);
        for (boolean b : v) if (!b) return b;
        return true;
    }

    private void dfs(boolean[] v, int i, List<List<Integer>> r) {
        if (v[i]) return;
        v[i] = true;
        List<Integer> keys = r.get(i);
        for (Integer k : keys) dfs(v, k, r);
    }
}
