package leetcode_old;

import java.util.HashMap;

class SetElement {
    public int v;
    public SetElement parent = null;

    public SetElement(int i) {
        v = i;
    }

    public SetElement find() {
        SetElement t = this;
        while (t.parent != null) t = t.parent;
        return t;
    }

    public void union(SetElement j) {
        SetElement t = this;
        while (t.parent != null) t = t.parent;
        j.find().parent = t;
    }
}

public class RedundantConnection {

    /**
     * this is a graph problem, try to find a cyclic in a graph
     * consider disjoint set mechanism
     * */
    public static int[] findRedundantConnection(int[][] edges) {
        if (edges == null ||
                edges.length == 0 ||
                edges[0] == null ||
                edges[0].length == 0) return null;

        HashMap<Integer, SetElement> set = new HashMap<>();
        for (int[] e : edges) {
            if (!set.containsKey(e[0])) set.put(e[0], new SetElement(e[0]));
            if (!set.containsKey(e[1])) set.put(e[1], new SetElement(e[1]));
        }
        for (int[] e : edges) {
            SetElement s1 = set.get(e[0]);
            SetElement s2 = set.get(e[1]);
            if (s1.find() == s2.find()) return e;
            else {
                s1.union(s2);
            }
        }
        return null;
    }
}
