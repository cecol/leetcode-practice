package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC347TopKFrequentElements extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC347TopKFrequentElements();
    }

    /**
     * 這題很明顯用 HashMap 累計次數後再用 Heap挖出前 k 個計數
     * 我有看到題目限制 O(n log n), 原本以為 Heap做法行不通, 因為如果所有元素都進入 Heap 也應該是 O(n log n)
     * 但後來跑一下: faster than 86.02%, 我猜大概是重複很多, 題目也有提到 answer is unique
     * 所以應該只有一種答案, HashMap 累計次數後的元素數目應該不多, 塞進heap 效率就快多了
     * 比較多的答案都是 bucket sort,
     * 也是先HashMap 累計次數, 然後 建立 bucket[nums.length], 應該比heap快一點 -> faster than 92.73%
     *
     */
    public int[] topKFrequentBucket(int[] nums, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int n : nums) m.put(n, m.getOrDefault(n, 0) + 1);
        List<Integer>[] bucket = new List[nums.length + 1];
        for (int n : m.keySet()) {
            int freq = m.get(n);
            if (bucket[freq] == null)
                bucket[freq] = new LinkedList<>();
            bucket[freq].add(n);
        }
        List<Integer> res1 = new LinkedList<>();
        int bk = k;
        for (int i = bucket.length - 1; i > 0 && bk > 0; --i) {
            if (bucket[i] != null) {
                List<Integer> list = bucket[i];
                res1.addAll(list);
                bk -= list.size();
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) res[i] = res1.get(i);
        return res;
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int n : nums) m.put(n, m.getOrDefault(n, 0) + 1);
        PriorityQueue<Integer[]> h = new PriorityQueue<Integer[]>((i1, i2) -> -i1[1] - -i2[1]);
        for (Integer kk : m.keySet()) h.add(new Integer[]{kk, m.get(kk)});
        int[] res = new int[k];
        for (int i = 0; i < k; i++) res[i] = h.poll()[0];
        return res;
    }
}
