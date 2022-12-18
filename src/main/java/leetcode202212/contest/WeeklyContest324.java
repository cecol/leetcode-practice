package leetcode202212.contest;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;


public class WeeklyContest324 extends BasicTemplate {
    public static void main(String[] args) throws IOException {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new WeeklyContest324();
//        LC.smallestValue(3);
//        LC.smallestValue(15);

//
//        LC.isPossible(21, p);
//        LC.isPossible(4, List.of(List.of(1, 2), List.of(3, 4)));
//        LC.isPossible(6, List.of(List.of(1, 2), List.of(1, 3), List.of(1, 4), List.of(4, 5), List.of(5, 6)));
        //noinspection RedundantTypeArguments (explicit type arguments speedup compilation and analysis time)
//        LC.isPossible(11, List.<List<Integer>>of(List.of(5, 9), List.of(8, 1), List.of(2, 3), List.of(7, 10), List.of(3, 6), List.of(6, 7), List.of(7, 8), List.of(5, 1), List.of(5, 7), List.of(10, 11), List.of(3, 7), List.of(6, 11), List.of(8, 11), List.of(3, 4), List.of(8, 9), List.of(9, 1), List.of(2, 10), List.of(9, 11), List.of(5, 11), List.of(2, 5), List.of(8, 10), List.of(2, 7), List.of(4, 1), List.of(3, 10), List.of(6, 1), List.of(4, 9), List.of(4, 6), List.of(4, 5), List.of(2, 4), List.of(2, 11), List.of(5, 8), List.of(6, 9), List.of(4, 10), List.of(3, 11), List.of(4, 7), List.of(3, 5), List.of(7, 1), List.of(2, 9), List.of(6, 10), List.of(10, 1), List.of(5, 6), List.of(3, 9), List.of(2, 6), List.of(7, 9), List.of(4, 11), List.of(4, 8), List.of(6, 8), List.of(3, 8), List.of(9, 10), List.of(5, 10), List.of(2, 8), List.of(7, 11)));
        LC.isPossible(20, List.of(List.of(2, 7), List.of(6, 20), List.of(7, 19), List.of(12, 13), List.of(4, 9), List.of(11, 20), List.of(11, 13), List.of(3, 6), List.of(3, 7), List.of(3, 4), List.of(1, 8), List.of(18, 4), List.of(16, 6), List.of(6, 11), List.of(9, 16), List.of(15, 4), List.of(13, 3), List.of(14, 3), List.of(18, 12), List.of(8, 14), List.of(15, 2), List.of(7, 15), List.of(4, 11), List.of(13, 20), List.of(20, 18), List.of(20, 10), List.of(20, 3), List.of(15, 3), List.of(4, 8), List.of(10, 1), List.of(19, 15)));
    }

    public int similarPairs(String[] words) {
        int count = 0;
        int n = words.length;
        String[] wc = new String[n];
        for (int i = 0; i < n; i++) wc[i] = wc(words[i]);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (wc[i].equals(wc[j])) count++;
            }
        }
        return count;
    }

    String wc(String s) {
        int[] cc = new int[256];
        for (char c : s.toCharArray()) {
            cc[c] = 1;
        }
        return Arrays.toString(cc);
    }

    public int smallestValue(int n) {
        int res = decompose(n);
        while (decompose(n) != n) {
            int v = decompose(n);
            res = Math.min(res, v);
            n = v;
        }
        return res;
    }

    int decompose(int n) {
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            while (n % i == 0 && n != i) {
                n /= i;
                sum += i;
            }
            if (n == i) {
                sum += i;
                break;
            }
        }
        return sum;
    }

    public boolean isPossible(int n, List<List<Integer>> edges) {
        Set<Integer>[] graph = new HashSet[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new HashSet<>();
        for (List<Integer> e : edges) {
            graph[e.get(0)].add(e.get(1));
            graph[e.get(1)].add(e.get(0));
        }
        List<Integer> odd = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (graph[i].size() % 2 == 1) odd.add(i);
        }
        if (odd.size() == 0) return true;

        if (odd.size() == 2) {
            int x = odd.get(0);
            int y = odd.get(1);
            if (!graph[x].contains(y)) return true;
            for (int i = 1; i <= n; i++) {
                if (i != x && i != y) if (!graph[i].contains(x) && !graph[i].contains(y)) return true;
            }
        }

        if (odd.size() == 4) {
            int edgeCount = 0;
            for (int i = 0; i < 4; i++) {
                int x = odd.get(i);
                for (int j = i + 1; j < 4; j++) {
                    int y = odd.get(j);
                    if (x != y && graph[x].contains(y)) edgeCount++;
                }
            }
            return edgeCount == 2 || edgeCount == 4 || edgeCount == 0 || edgeCount == 1;
        }

        return false;
    }
}