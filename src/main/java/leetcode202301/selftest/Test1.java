package leetcode202301.selftest;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;


public class Test1 extends BasicTemplate {
    public static void main(String[] args) throws IOException {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new Test1();
        LC.requestsServed(List.of(0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1), List.of(6, 6, 6, 6));
    }

    public int requestsServed(List<Integer> timestamp, List<Integer> top) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (Integer t : timestamp) tm.put(t, tm.getOrDefault(t, 0) + 1);

        int res = 0;
        for (Integer p : top) {
            int count = 5;
            while (tm.floorKey(p) != null && count > 0) {
                Integer floor = tm.floorKey(p);
                if (tm.get(floor) <= count) {
                    res += tm.get(floor);
                    count -= tm.get(floor);
                    tm.remove(floor);
                } else {
                    res += count;
                    tm.put(floor, tm.get(floor) - count);
                    count = 0;
                }
            }
        }
        return res;
    }

    public String getString(String s) {
        char[] cs = s.toCharArray();
        Set<Character> visited = new HashSet<>();
        for (int i = 0; i < cs.length - 1; i++) {
            if (i > 0 && cs[i] == cs[i - 1]) continue;
            int min = i;
            for (int j = i + 1; j < cs.length; j++) {
                if (cs[j] < cs[min] && !visited.contains(cs[j])) min = j;
            }
            if (i != min && cs[i] > cs[min]) {
                visited.add(cs[min]);
                swap(cs, cs[i], cs[min]);
            }
        }

        return new String(cs);
    }

    void swap(char[] cs, char c1, char c2) {
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == c1) cs[i] = c2;
            else if (cs[i] == c2) cs[i] = c1;
        }
    }

    public int solution1(int[][] a) {
        int m = a.length, n = a[0].length;
        HashSet<Integer>[] docs = new HashSet[m];
        for (int i = 0; i < m; i++) {
            docs[i] = new HashSet<>();
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                docs[i].add(a[i][j]);
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            HashSet<Integer> doci = docs[i];
            for (int j = i + 1; j < m; j++) {
                HashSet<Integer> docj = docs[j];
                for (Integer di : doci) {
                    if (docj.remove(di)) res++;
                }
            }
        }
        return res;
    }

    public int solutionPreSum(String s) {
        int res = 0;
        int n = s.length();
        int[] count = new int[256];
        Map<String, Integer> preCount = new HashMap<>();
        preCount.put(Arrays.toString(count), -1);
        for (int i = 0; i < n; i++) {
            count[s.charAt(i)] = (count[s.charAt(i)] + 1) % 2;
            String countKey = Arrays.toString(count);
            if (preCount.containsKey(countKey)) res = Math.max(res, i - preCount.get(countKey));
            else preCount.put(countKey, i);
        }

        return res;
    }

    public int solutionPreSumOptimized(String s) {
        int res = 0;
        int n = s.length();
        int state = 0;
        Map<Integer, Integer> preCount = new HashMap<>();
        preCount.put(0, -1);
        for (int i = 0; i < n; i++) {
            int offset = (1 << (s.charAt(i) - 'a'));
            if ((state & offset) >= 1) {
                state = state ^ offset;
            } else state = state | offset;
            if (preCount.containsKey(state)) res = Math.max(res, i - preCount.get(state));
            else preCount.put(state, i);
        }
        return res;
    }
}