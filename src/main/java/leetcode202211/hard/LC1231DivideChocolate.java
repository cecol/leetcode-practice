package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC1231DivideChocolate extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1231DivideChocolate();
    }

    public int maximizeSweetness(int[] sweetness, int k) {
        int l = 0, r = Integer.MAX_VALUE;
        while (l < r) {
            int mid = r - (r - l) / 2;
            if (possible(sweetness, k, mid)) l = mid;
            else r = mid - 1;
        }
        return l;
    }

    boolean possible(int[] sweetness, int k, int mid) {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < sweetness.length; i++) {
            sum += sweetness[i];
            if (sum >= mid) {
                sum = 0;
                count++;
            }
        }
        return count >= k + 1;
    }
}
