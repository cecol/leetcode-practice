package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.PriorityQueue;
import java.util.Stack;

public class LC774MinimizeMaxDistanceToGasStation extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC774MinimizeMaxDistanceToGasStation();
    }

    /**
     * 我有想出基本架構 就是 Binary search 從最小 0 to 最大 1e8 來找可能值
     * 只是說有效的確認 能否把 int[] stations, 在切 k 次後 確保 max distance < mid , 沒想出來
     * <p>
     * https://leetcode.com/problems/minimize-max-distance-to-gas-station/discuss/750644/I-just-could-not-understand-this-question-intuitive-figures-with-explanation
     * 在於確認是否再加上 k stations 後 能否達成 <= mid distance
     * 應該用另一個方式去想
     * 如果 stations[i] & stations[i+1] 之間距離 <= mid, 那就不用加 station 來縮短距離
     * 如果 stations[i] & stations[i+1] 之間距離 > mid, 就要看要加幾個 station 來達成之間距離 <= mid
     * 最終得確保在任何 stations[i] & stations[i+1] 之間距離 <= mid, 共加了幾個 station
     * 如果總數 <= k 那 can(int[] stations, int k, double m) 就是 true
     * <p>
     * 正解
     * https://leetcode.com/problems/minimize-max-distance-to-gas-station/discuss/113633/C%2B%2BJavaPython-Binary-Search
     */
    public double minmaxGasDist(int[] stations, int k) {
        double l = 0, r = 1e8;
        while (l + 0.000001 < r) {
            double mid = l + (r - l) / 2;
            if (can(stations, k, mid)) {
                r = mid;
            } else l = mid + 0.000001;
        }
        return l;
    }

    boolean can(int[] stations, int k, double m) {
        int count = 0;
        for (int i = 0; i < stations.length - 1; i++) {
            count += Math.ceil((stations[i + 1] - stations[i]) / m) - 1;
        }
        return count <= k;
    }
}
