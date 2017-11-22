package leetcode_old;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TopKFrequent {
    public static void main(String[] args) {
        int[] n1 = {1, 1, 1, 2, 2, 3};
        List<Integer> r = topKFrequent(n1, 2);
        for (Integer i : r) System.out.print(i + " ");
        System.out.println();
    }

    public static List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> list = new LinkedList<Integer>();
        if (nums == null) return list;
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        for (int i : nums) {
            int c = count.getOrDefault(i, 0) + 1;
            count.put(i, c);
        }
        for (Map.Entry<Integer, Integer> e : count.entrySet()) System.out.println(e.getKey() + " " + e.getValue() + " ");
        for (; k > 0; k--) {
            int max = 0;
            int mp = 0;
            for (Map.Entry<Integer, Integer> e : count.entrySet()) {
                if (e.getValue() > max) {
                    mp = e.getKey();
                    max = e.getValue();
                }
            }
            count.remove(mp);
            list.add(mp);
        }

        return list;
    }

    public static List<Integer> topKFrequent2(int[] nums, int k) {
        List<Integer> list = new LinkedList<Integer>();
        if (nums == null) return list;
        LinkedList[] bucket = new LinkedList[nums.length];
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        for (int i : nums) {
            int c = count.getOrDefault(i, 0) + 1;
            count.put(i, c);
        }
        for (Integer key : count.keySet()) {
            int v = count.get(key);
            if (bucket[v] == null) {
                bucket[v] = new LinkedList<>();
            }
            bucket[v].add(key);
        }
        for (int j = bucket.length - 1; k > list.size() && j > 0; j--) {
            if(bucket[j] != null){
                list.addAll(bucket[j]);
            }
        }
        return list;
    }
}
