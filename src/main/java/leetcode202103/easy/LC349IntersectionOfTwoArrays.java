package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC349IntersectionOfTwoArrays extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC349IntersectionOfTwoArrays();
    }

    /**
     * 原本覺得應該沒這麼蠢 要先sort再用 set
     * https://leetcode.com/problems/intersection-of-two-arrays/discuss/81969/Three-Java-Solutions
     * 但看了答案之後覺得就是這樣吧
     * */
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Set<Integer> res = new HashSet<>();
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if(nums1[i] < nums2[j]) i++;
            else if(nums1[i] > nums2[j]) j++;
            else {
                res.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] r = new int[res.size()];
        int k = 0;
        for (Integer p : res) r[k++] = p;
        return r;
    }
}
