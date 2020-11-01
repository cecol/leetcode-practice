package leetcode20200921to20201031.medium.algo;

import leetcode20200921to20201031.BasicTemplate;

public class NumArrayBIT extends BasicTemplate {
    public int[] nums = null;
    public int[] bit = null;

    public NumArrayBIT(int[] nums) {
        this.nums = nums;
        this.bit = new int[nums.length + 1];
        buildTree();
    }

    public void buildTree() {
        for (int i = 1; i < bit.length; i++) {
            int n = i;
            bit[n] += nums[i - 1];
            n += (n & -n);
            while (n < bit.length) {
                bit[n] += nums[i - 1];
                n += (n & -n);
            }
        }
    }

    public void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        i++;
        bit[i] += diff;
        i += (i & -i);
        while (i < bit.length) {
            bit[i] += diff;
            i += (i & -i);
        }
    }

    public int sumRange(int i, int j) {
        int sj = 0;
        int si = 0;
        j++;
        while (j != 0) {
            sj += bit[j];
            j -= j & -j;
        }
        while (i != 0) {
            si += bit[i];
            i -= i & -i;
        }
        return sj - si;
    }
}
