package leetcode202210.contest;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.*;

public class WeeklyContest317 extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new WeeklyContest317();
        log.debug("{}", LC.makeIntegerBeautiful(467, 6));
        log.debug("{}", LC.makeIntegerBeautiful(6068060761L, 6));
    }

    public int averageValue(int[] nums) {
        float c = 0;
        float s = 0;
        for (int n : nums)
            if (n % 2 == 0 && n % 3 == 0) {
                s += n;
                c++;
            }
        if (c == 0) return 0;
        float res = s / c;
        return (int) res;
    }

    class Zip {
        String creator, id;
        int v;

        Zip(String c, String id, int views) {
            this.creator = c;
            this.id = id;
            this.v = views;
        }
    }

    class POP {
        String id;
        int v;

        POP(String id, int views) {
            this.id = id;
            this.v = views;
        }
    }

    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        int n = creators.length;
        Zip[] zip = new Zip[n];
        Map<String, Integer> pop = new HashMap<>();
        Map<String, PriorityQueue<POP>> mpop = new HashMap<>();
        for (int i = 0; i < n; i++) {
            zip[i] = new Zip(creators[i], ids[i], views[i]);
        }
        for (Zip z : zip) {
            pop.put(z.creator, pop.getOrDefault(z.creator, 0) + z.v);
            if (!mpop.containsKey(z.creator)) mpop.put(z.creator, new PriorityQueue<>((x, y) -> {
                if (x.v == y.v) return x.id.compareTo(y.id);
                else return -x.v - (-y.v);
            }));
            mpop.get(z.creator).offer(new POP(z.id, z.v));
        }
        int max = 0;
        for (Map.Entry<String, Integer> en : pop.entrySet()) {
            if (en.getValue() > max) max = en.getValue();
        }
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<String, Integer> en : pop.entrySet()) {
            if (en.getValue() == max) {
                List<String> r = new ArrayList<>();
                r.add(en.getKey());
                r.add(mpop.get(en.getKey()).poll().id);
                res.add(r);
            }
        }
        return res;
    }

    public long makeIntegerBeautiful(long n, int target) {
        if (bitSum(n) <= target) return 0;
        long s = 0;
        long m = 10;
        long nn = n;
        while (bitSum(nn) > target) {
            s += m - nn % m;
            nn = n + s;
            m *= 10;
        }
        return s;
    }

    long bitSum(long n) {
        long s = 0;
        for (char c : String.valueOf(n).toCharArray()) {
            s += c - '0';
        }
        return s;
    }

    Map<TreeNode, Integer> m = new HashMap<>();
    Map<TreeNode, List<TreeNode>> ans = new HashMap<>();

    public int[] treeQueries(TreeNode rt, int[] queries) {
        buildAns(rt, new ArrayList<>());
        for (int i = 0; i < queries.length; i++) {
            findRm(rt, queries[i]);
            queries[i] = h(rt, queries[i]) - 1;
        }
        return queries;
    }

    void buildAns(TreeNode rt, List<TreeNode> as) {
        if (rt == null) return;
        ans.put(rt, as);
        List<TreeNode> newAs = new ArrayList<>(as);
        newAs.add(rt);
        buildAns(rt.left, newAs);
        buildAns(rt.right, newAs);
    }

    boolean findRm(TreeNode rt, int t) {
        if (rt == null) return false;
        if (rt.val == t) {
            for (TreeNode a : ans.get(rt)) m.remove(a);
            return true;
        }
        boolean f = findRm(rt.left, t);
        if (f) return true;
        return findRm(rt.right, t);
    }

    int h(TreeNode rt, int t) {
        if (rt == null) return 0;
        if (rt.val == t) return 0;
        if (m.containsKey(rt)) return m.get(rt);
        int res = Math.max(h(rt.left, t), h(rt.right, t)) + 1;
        m.put(rt, res);
        return res;
    }

}
