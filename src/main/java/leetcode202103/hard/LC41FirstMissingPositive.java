package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.PriorityQueue;

public class LC41FirstMissingPositive extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC41FirstMissingPositive();
    }

    /**
     * https://leetcode.com/problems/first-missing-positive/discuss/17214/Java-simple-solution-with-documentation
     * 這題太精妙了, 不知道該怎麼想出來的
     * 1. 要找的是 1, 2, 3 ... 所以 nums 中 <= 0 的都可以省略
     * 2. 把 nums 裡面的正整數切分成2份:
     * -> 1. 1 to n
     * -> 2. n+1 to infinite
     * 3. 第一輪把 nums[i] 把負數與 大於 n+1 都設定成 n+1: if(nums[i] <=0 || nums[i] > n) nums[i] = n+1;
     * 4. 所以當前 nums[i] 只會是 n+1 or 1 to n
     * 5. 第二輪把 nums[i]中 1 to n 當成 offset, 去把對應位置的值->如果是正數, 改成負數
     * 6. 如果 nums[i]中
     * -> 全是負數 -> 代表 nums 裡面正好存 1 to n -> 所以下個最小正整數就是 n+1
     * -> 左邊數來第一個找到是正數 -> 代表缺的是該數(所以改 index沒被改成負數)
     * 因為有在 nums 的 1 to n 都拿來當index, 把該位置改成負數 -> 沒被改到的代表 根本沒有該數, 代表被多放了
     * 1. 比n還大
     * 2. 小於等於0
     * 這題關鍵就是
     * 1. 先區分 1 to n 之間, 並拿 nums[i]中 1 to n 來當 index, 來把該位置改成負數
     * -> 沒被改到的, 就是缺乏的正整數
     * -> 因為要處理 0 與負數還有 大於n -> 所以有第一個for 來處理 0 與負數把他們設定成 n+1
     * */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for(int i=0;i<n;i++) {
            if(nums[i] <=0 || nums[i] > n) nums[i] = n+1;
        }

        for(int i=0;i<n;i++) {
            int num = Math.abs(nums[i]);
            if(num > n) continue;
            int idx = num - 1;
            if(nums[idx] > 0) nums[idx] *= -1;
        }

        for(int i=0; i< n;i++) if(nums[i]>=0) return i+1;
        return n+1;
    }
}
