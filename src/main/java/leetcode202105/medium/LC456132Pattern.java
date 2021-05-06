package leetcode202105.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LC456132Pattern extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC456132Pattern();
    }

    /**
     * 想了很久都沒有好的解法, 直觀暴力解是 Ｏ（ｎ＾３）但會 TLE
     * 連O(n^2) 都沒想出來
     * 這篇有很仔細解釋 O(N^3), O(N^2), O(N) 等方法, 但我沒有很懂後面的
     * https://leetcode.com/problems/132-pattern/discuss/94089/Java-solutions-from-O(n3)-to-O(n)-for-%22132%22-pattern-(updated-with-one-pass-slution)
     * stack 的 O(n) 是這篇有比較懂
     * https://leetcode.com/problems/132-pattern/discuss/906789/Short-Java-O(N)-Solution-with-Detailed-Explanation-and-Sample-Test-Case-or-Stack-or-100
     * 1. 從尾巴開始iterate
     * 2. 用一個 global variable second 記憶 2 pattern 就是 nums[i] < nums[k] < nums[j] 中的 nums[j]
     * 3. stack 記憶 32 pattern, 然後看看目前走到是否有 1 pattern
     * -> 所以 stack 記憶的是 由top -> bottom 是遞增
     * -> 如果當前 nums[i] > stack.top -> second = stack.pop();
     * -> 因為stack 代表的是之前走過的, 所以 nums[i] > stack.top 都是符合 32 pattern, nums[i]是 3, stack.pop() 是2
     * -> 會用 while(sk.size() > 0 && sk.peek() < nums[i]) second = sk.pop();
     * -> 是因為second 要盡可能最大(但也不可以超越當前nums[i], 當前nums[i]是 3 pattern), 這樣下一圈的 nums[i] 才有機會 < second
     * 4. sk.push(nums[i]), 因為還沒找到132 pattern, 前面也檢查過了 sk裡面都比目前nums[i] 小
     * -> 所以放入 stack, 當前 nums[i] 看能不能變成之後的 pattern 2
     *
     * 簡化來說 stack 記憶 32 pattern, stack top是最大的, 是3 pattern, global variable second 記憶 2 pattern
     * 然後當前 nums[i] < second 就是答案
     * 所以 in for loop for(int i= nums.length-1;i>=0;i--)
     * 1. if(nums[i] < s2p) return true; 先試著把 nums[i] 當作 1 pattern 來看看前面走過的是否有出現32 pattern
     * 2. while(sk.size()>0 && sk.peek()< nums[i]) second = sk.pop()
     * -> 把 nums[i] 當作 1 pattern 失敗後, 看看是否 nums[i] 是不是能當成 pattern 3, 然後取出 pattern 2, 讓下一圈檢查看看
     * 3. sk.push(nums[i]); 當前 nums[i] 當作 2 candidate 放入
     * 所以 stack 中都是放 2 pattern 的 candidate, 配上因為loop是從右邊走到左邊 -> 因為array右邊是要找 32 pattern
     * 所以往前走如果 sk.peek() < nums[i] 代表 sk 裡面的可以變成 2 pattern, nums[i] 當成3 pattern
     * 每當找到 sk.peek() < nums[i] 就代表可以更新 2 pattern
     * 如果 stack 底層有很大的值, 代表他雖然可以當2 pattern, 但因為在太大了, 左邊沒有 pattern 3 , 所以一直卡在 stack 底部
     *
     * 用 stack 解真的太玄妙了, 也沒把握是否能時間內想出
     * O(n^2) 就算快了 faster than 44%
     */
    public boolean find132pattern(int[] nums) {
        Stack<Integer> sk = new Stack<Integer>();
        int second = Integer.MIN_VALUE;
        for(int i=nums.length-1;i>=0;i++) {
            if(nums[i] < second) return true;
            while(sk.size() > 0 && sk.peek() < nums[i]) second = sk.pop();
            sk.push(nums[i]);
        }
        return false;
    }

    /**
     * 連O(n^2) 其實可以很直觀, 重點就是固定中間點 j, 然後記憶 j 左邊的min,
     * 第二層迴圈去找 j 左邊的有出現 nums[k] < nums[j] && nums[k] > min
     * */
    public boolean find132patternOn2(int[] nums) {
        for (int j = 0, min = Integer.MAX_VALUE; j < nums.length - 1; j++) {
            min = Math.min(nums[j], min);
            if (nums[j] == min) continue;
            for (int k = j + 1; k < nums.length; k++) {
                if (nums[k] < nums[j] && nums[k] > min) return true;
            }
        }
        return false;
    }
}
