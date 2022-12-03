package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC857MinimumCostToHireKWorkers extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC857MinimumCostToHireKWorkers();
    }

    /**
     * https://leetcode.com/problems/minimum-cost-to-hire-k-workers/discuss/641452/Java-Clean-Code-with-Comments
     * 這題真的超不直觀
     * 直接先看如果 wage[i]/quality[i] , 高的代表工資貴品質低,
     * 然後又有條件大家的比例要一樣, 所以 wage[i]/quality[i] 高的人就會把其他人價錢拉高
     * 原意是 we have wage[i] : wage[j] = quality[i] : quality[j] 數學轉換
     * -     So we have wage[i] : quality[i] = wage[j] : quality[j]
     * 比如說 wage[i]/quality[i] > wage[j]/quality[j], 所以 wage[j] 得上漲才會達成和 wage[i]/quality[i] 一樣比例
     * 原本說 wage[i] 是最低需求工資, 我這邊被困惑了, 以為要是最低 vs 被比例拉高後許最大值來算工資
     * 但其實先算出 wage[i]/quality[i] 從小排到大 加入 k group
     * wage[i]/quality[i] 小的 wage 其實就會被 後面大的 wage[j]/quality[j] 拉上來
     * 一定滿足最低工資, 所以根本不用去算,
     * 要算的是, 當前累計的 quality sum * 當前看到的最大 wage[i]/quality[i]
     * 相乘就是 整體 wage
     *
     * 1.  double[][] wqRatio[i] = { wage[i]/quality[i], quality[i] }
     * - Sort wqRatio -> 有小到大
     * 2. go through wqRatio -> 都加入 k, 把 quality 加入 max_heap
     * 因為我們是 sort wqRatio by wage[i]/quality[i] ratio
     * 有可能有人 ratio 很低但 quality 很高, 在算 quality sum * 當前看到的最大 wage[i]/quality[i] 會把整體工資拉高
     * 所以當我們有看到 K+1 個工人後, 我們優先拿掉 quality 大的來看看是不是整體工資有下降！！
     * 這就是 max heap 作用地方
     * 這邊就是兩者優先權在互相作用過程中來找最小值
     * 1. wqRatio, wage[i]/quality[i] 優先考量 -> 畢竟 wage[i]/quality[i] 越大只會更貴
     * 2. max heap -> 高 quality 先考慮拿掉看能否降工資
     * 上面兩著交替計算找出 minimun cost
     * */
    public double mincostToHireWorkers(int[] q, int[] w, int k) {
        int n = q.length;
        double[][] wqRatio = new double[n][2];
        for (int i = 0; i < n; i++)
            wqRatio[i] = new double[]{(double) (w[i]) / q[i], q[i]};
        Arrays.sort(wqRatio, (x, y) -> Double.compare(x[0], y[0]));
        double res = Double.MAX_VALUE, qsum = 0;
        PriorityQueue<Double> pq = new PriorityQueue<>((x, y) -> Double.compare(y, x));
        for (double[] worker : wqRatio) {
            qsum += worker[1];
            pq.add(worker[1]);
            if (pq.size() > k) qsum -= pq.poll();
            if (pq.size() == k) res = Math.min(res, qsum * worker[0]);
        }

        return res;
    }
}
