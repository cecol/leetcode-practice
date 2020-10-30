package leetcode202009.medium;

import leetcode202009.BasicTemplate;
import leetcode202009.medium.subclass.NumArrayBIT;

public class LC307RangeSumQueryMutable extends BasicTemplate {

    public static void main(String[] args) {
        var LC = new NumArrayBIT(new int[]{9, -8});
        LC.update(0, 3);
        LC.sumRange(1, 1);
        LC.update(0, 1);
        LC.update(1, -3);
        LC.update(0, 1);
    }
}
