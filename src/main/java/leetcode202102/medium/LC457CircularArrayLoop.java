package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC457CircularArrayLoop extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC457CircularArrayLoop();
    }

    /**
     * 完全沒什麼頭緒 看了答案結果是完全不知道的  Slow/Fast Pointer Solution 概念
     * https://leetcode.com/problems/circular-array-loop/discuss/94148/Java-SlowFast-Pointer-Solution
     * 基本概念就是:
     * 1. 每一個點都下去檢查是否有成為loop -> 透過slow/fast兩個pointer下去走, 最後 slow == fast -> 就是 回傳true
     * -> 過程中是一個while, inner loop, 看起來會是O(n^2),但因為下面的case會把走過的標示掉, 所以實際上是O(n)
     * -> 這個while 條件 : nums[i] * nums[fast] > 0 && nums[i] * nums[advance(nums, fast)] > 0
     * -> 相乘>0 代表過下一步 下下步方向都是一致往前(or往後, 以nums[i]為基礎)
     * -> 然後過程中開始走 slow = 一步一步走, fast = 一次走兩步, 那麼fast最後如果有追上 slow
     * -> 為什麼 fast是兩步？不是1,3,4,5..步? 1步就跟slow平行,永遠追不上, 3步可能導致 fast就直接走回自己(loop只有3個)
     * -> 所以只有fast走兩步才能一個個確認
     * -> 且並沒有slow == advance(nums, slow) 就是說自己迴圈
     * -> 那就是答案了, 直接return
     * -> 如果不是, 一定在過程中 fast會改變方向, 導致沒有loop 而跳出while 那麼就進入下面的case
     * 2. 如果該點沒有造成 loop -> 其實可以不要理他, 但會造成效能低落(O(n^2))
     * ->  所以把該點經過的路徑都標成0(0就是自己loop, 下一圈遇到會直接跳過:  if (nums[i] == 0) continue; )
     * ->  這邊很有趣的是 while條件 sgn * nums[slow] > 0
     * ->  其實就是找i 開始後面跟他相同方向的下一步都標成0
     * ->  為什麼？ 因為沒造成的負數 就是導致nums[i]起點改變方向沒有造成loop,
     * ->  造成負數的該點可能配合其他跟他同方向的是loop, 只有i 跟後面跟他同方向的是 non loop, 所以就往前找找倒轉則點就停下來
     */
    int len = 0;

    public boolean circularArrayLoop(int[] nums) {
        if (nums == null || nums.length < 2) return false;
        len = nums.length;
        // check every possible start location
        for (int i = 0; i < len; i++) {
            // if 0 -> we are not on a circle loop, continue to next start location to check
            if (nums[i] == 0) continue;
            int slow = i, fast = advance(nums, i);
            while (nums[i] * nums[fast] > 0 && nums[i] * nums[advance(nums, fast)] > 0) {
                if (slow == fast) {
                    if (slow == advance(nums, slow)) break;
                    return true;
                }
                slow = advance(nums, slow);
                fast = advance(nums, advance(nums, fast));
            }
            slow = i;
            int sgn = nums[i];
            while (sgn * nums[slow] > 0) {
                int tmp = advance(nums, slow);
                nums[slow] = 0;
                slow = tmp;
            }
        }
        return false;
    }

    // move the pointer i ahead one iteration
    private int advance(int[] n, int i) {
        i += n[i];
        i%=len;
        while (i<0) i+=len;
        return i;
    }
}
