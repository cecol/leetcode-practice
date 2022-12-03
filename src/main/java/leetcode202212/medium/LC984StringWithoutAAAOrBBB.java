package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.PriorityQueue;
import java.util.Stack;

public class LC984StringWithoutAAAOrBBB extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC984StringWithoutAAAOrBBB();
        LC.strWithout3a3b(2,5);
    }

    /**
     * 這題基本上跟 LC358RearrangeStringKDistanceApart LC767ReorganizeString
     * 完全邏輯一樣, 只是只有兩個字元 a/b 要不斷交錯進出 PriorityQueue<int[]> pq
     * 然後看看 sb 裏面是否連續出現 2 次重複
     * */
    public String strWithout3a3b(int a, int b) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> y[1] - x[1]);
        pq.offer(new int[]{(int) 'a', a});
        pq.offer(new int[]{(int) 'b', b});
        while (pq.size() > 0) {
            int[] c = pq.poll();
            if (sb.length() <= 1 ||
                    sb.charAt(sb.length() - 1) != c[0] ||
                    (sb.length() > 1 && sb.charAt(sb.length() - 2) != c[0]) || pq.size() == 0) {
                sb.append((char) c[0]);
                c[1]--;
                if (c[1] > 0) pq.offer(c);
            } else {
                int[] c2 = pq.poll();
                sb.append((char) c2[0]);
                c2[1]--;
                if (c2[1] > 0) pq.offer(c2);
                pq.offer(c);
            }
        }
        return sb.toString();
    }
}
