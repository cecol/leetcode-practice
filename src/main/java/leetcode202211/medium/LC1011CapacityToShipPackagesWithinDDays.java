package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC1011CapacityToShipPackagesWithinDDays extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * 跟 410 Split Array Largest Sum 完全一樣
     * ship 最重可以是 sum of weights, 1 day 運完
     * ship 最輕可以是 max of weights, len(weights) day 運完
     * <p>
     * 現在有 給 k days, 找出最小 ship 重量
     */
    public int shipWithinDays(int[] weights, int days) {
        long sum = 0;
        int max = 0;
        for (int w : weights) {
            sum += w;
            max = Math.max(max, w);
        }
        if (days == 1) return (int) sum;
        if (days == weights.length) return max;

        long l = max, r = sum;
        while (l < r) {
            long mid = l + (r - l) / 2;
            if (canShipWithinDays(mid, weights, days)) r = mid;
            else l = mid + 1;

        }
        return (int) l;
    }

    boolean canShipWithinDays(long maxWeight, int[] nums, int d) {
        int needDay = 1;
        long currentWeight = 0;
        for (int w : nums) {
            currentWeight += w;
            if (currentWeight > maxWeight) {
                needDay++;
                currentWeight = w;
                if (needDay > d) return false;
            }
        }
        return true;
    }
}
