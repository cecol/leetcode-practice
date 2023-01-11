package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC330PatchingArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC330PatchingArray();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Greedy/330.Patching-Array
     * 這題觀念超級難, 題目其實也不是很直觀
     *
     * 給你 [1,2,3] 跟 n = 20 然後要你用 [1,2,3] 看能不能 sum 組出 [1,..,20]
     * ([1,2,3] 每個數字只能用一次去組 當前要組的對象 )
     * 如果中間有數字組不出來 看你還要給 [1,2,3] 補上多少數字 讓他可以繼續組下去  一路組到 20
     *
     * 用比較跳躍的想法
     * 假設當前 組不出來的數字 是 miss, 等於說 [1..miss-1] 你都組的出來
     * 獨缺 miss 數字
     * 這時候要補什麼? 要補 miss,  而且補上 miss 後 就一路可以組到 miss*2
     * 為什麼? 因為既然原本 [1..miss-1] 都組的出來了, 補上 miss 變成 [1..miss], 那
     * miss+1 = ([1]) + [miss]
     * miss+2 = ([2]) + [miss]
     * miss+3 = ([3]) + [miss]
     * miss+miss = ([1] + [miss-1]) + [miss]
     * ( ) 內都是原本 miss - 1 可以組的
     *
     * 所以邏輯是
     * 如果 nums[i] 還沒用完 且 nums[i] <= miss
     * miss 就可以往前擴大 到 miss + nums[i]
     * 為什麼?  因為已經有了 [1...miss-1] 組的出來, miss 本身組不出來
     * 但配上 nums[i], nums[i] <=  miss, 所以 [1..miss-1] 區間一定有值可以 nums[i] + (miss-nums[i]) == miss
     * 然後 [1..miss] 又可以配上 nums[i] 一路組到 miss+nums[i] 為最大
     *
     * 不然就是 增加 miss, 這時候 patch++, miss *= 2
     *
     * 如果 nums[i] > miss, 都缺 miss 了, nums[i] 只會更大  不會幫忙 miss, 所以只好補上 miss
     */
    public int minPatches(int[] nums, int n) {
        long miss = 1;
        int patch = 0, numsIdx = 0;
        while (miss <= n) {
            if (numsIdx < nums.length && nums[numsIdx] <= miss) {
                miss += nums[numsIdx++];
            } else {
                patch++;
                miss += miss;
            }
        }
        return patch;
    }
}
