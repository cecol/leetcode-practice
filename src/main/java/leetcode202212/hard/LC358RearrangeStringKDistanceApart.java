package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC358RearrangeStringKDistanceApart extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC358RearrangeStringKDistanceApart();
    }

    /**
     * https://leetcode.com/problems/rearrange-string-k-distance-apart/discuss/1125915/Clearly-Explained-Solution-or-JAVA-or-PriorityQueue
     * 這題其實跟 LC767ReorganizeString 一模一樣, LC767ReorganizeString 是 k = 2
     * LC767ReorganizeString, k=2 可以直接 first/second 交錯拿
     * 但是這題 如果 k == 3,4,5,6
     * 當前拿到的最大 count character 就加入一個 Queue<int[]> standbyK
     * 等待超過 k 個後續都被 append 後,
     * 再從 while (standbyK.size() >= k) {
     * - int[] standByEnough = standbyK.poll();
     * 挖出來再加入 pq 繼續加入 append 行列
     *
     * 這例列題目其實可以 O(N) 用很直接方式直接算距離把字串拼出來
     * 但是都好好算好每個 character 要插入的 offset 來 k 區間交錯
     * 但我覺得直接用 heap 來 配上 StringBuilder append 比較好處理, 不用在那邊算 插入的 offset 來 k 區間交錯
     */
    public String rearrangeString(String s, int k) {
        if(k == 0) return s;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            int count = map.getOrDefault(c, 0) + 1;
            map.put(c, count);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> y[1] - x[1]);
        for (Character c : map.keySet()) pq.offer(new int[]{c, map.get(c)});
        Queue<int[]> standbyK = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        while (pq.size() > 0) {
            int[] c = pq.poll();
            sb.append((char) c[0]);
            c[1]--;
            standbyK.add(c);
            while (standbyK.size() >= k) {
                int[] standByEnough = standbyK.poll();
                if (standByEnough[1] > 0) pq.offer(standByEnough);
            }
        }
        return s.length() == sb.length() ? sb.toString() : "";
    }
}
