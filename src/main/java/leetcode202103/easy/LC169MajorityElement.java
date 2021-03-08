package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;

public class LC169MajorityElement extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC169MajorityElement();
    }

    /**
     * https://leetcode.com/problems/majority-element/discuss/51613/O(n)-time-O(1)-space-fastest-solution
     * 想不到這題竟然有 paper
     * Boyer-Moore Majority Vote Algorithm
     * algorithm by him/her-self
     *
     * When count != 0 , it means nums[1...i] has a majority,which is major in the solution.
     * When count == 0 , it means nums[1...i ] doesn't have a majority, so nums[1...i ] will not help nums[1...n].And then we have a subproblem of nums[i+1...n].
     */
    public int majorityElement(int[] nums) {
        int major = nums[0];
        int c = 1;
        for (int i = 1; i < nums.length; i++) {
            if (c == 0) {
                c++;
                major = nums[i];
            } else if (major == nums[i]) {
                c++;
            } else c--;
        }
        return major;
    }
}
