package leetcode202009.easy;


import leetcode202009.BasicTemplate;

public class LC1313DecompressRunLengthEncodedList extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC1313DecompressRunLengthEncodedList();
        var s = LC.decompressRLElist(new int[]{2, 1, 1, 5});
    }

    public int[] decompressRLElist(int[] nums) {
        int s = 0;
        for (int i = 0; i < nums.length; i += 2) s += nums[i];
        log.debug("{}", s);
        int[] r = new int[s];
        s = 0;
        for (int i = 0; i < nums.length; i += 2)
            for (int j = nums[i]; j > 0; j--, s++)
                r[s] = nums[i + 1];

        log.debug("{}", r);
        return r;
    }

}
