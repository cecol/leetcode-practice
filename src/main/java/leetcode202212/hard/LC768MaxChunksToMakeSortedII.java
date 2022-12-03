package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Stack;

public class LC768MaxChunksToMakeSortedII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC768MaxChunksToMakeSortedII();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Stack/768.Max-Chunks-To-Make-Sorted-II
     * 解法一其實很直觀, 完全跟 stack 無關,
     * 排序原数组 arr 得到 expect arr，比较一下它们有什么区别
     * arr：[4,1,3,2],5,6
     * exp：[1,2,3,4],5,6
     * <p>
     * 所以 exp [1,2,3,4] 跟 [4,1,3,2] 區間 sum 一致, 只是 [4,1,3,2] 是有 permutation
     * 所以就是 for (int i = 0; i < arr.length; i++)
     * 1. 記載當前 expSum += expSum[i]
     * 2. 記載當前 arrSum += arrSum[i]
     * 3. 如果 arrSum == expSum, 代表找到區間, chunkCount++,  arrSum = 0, expSum = 0;
     * <p>
     * Stack 解法
     * 很不直觀, Stack 是 monotonic increase
     * chunk的特点是里面的所有元素都比
     * 1. chunk前 的大
     * 2. chunk後 的小
     * 3. chunk 內是 亂序
     *
     * Ex: .. 3, [7,8,4,6,5],9 ..
     * [3,7,8] in stack 都沒問題,
     * 但看到 4, 4太小, 4要到期望位置 就得 pop 7,8 -> [7,8,4] 就是一個 chunk
     * 但看到 6, 6太小, 6要到期望位置 就得 pop 7,8 -> [7,8,4,6] 就是一個 chunk
     * 但看到 5, 5太小, 6要到期望位置 就得 pop 7,8 -> [7,8,4,6,5] 就是一個 chunk
     * 關檢在於這個 chunk 最大是 8, 要記載 curMax = 8
     *
     * 所有违反递增规律的数都会被过滤掉。
     * 但是同一chunk里面没有违法递增规律的数还是会保留下来。
     * 根据上面的例子，我们的单调栈里面是3,[7,8],9...
     * 这时候我们想，如果我们能让每个chunk里面只保留一个数，那么最后栈里面剩多少个数不就意味着多少个chunk吗
     *
     * Ex: [7,8] chunk 只留 8
     *
     * 那么7通过什么方法去掉呢？其实我们当初在查看4的时候就提到过它会挤掉7和8.
     * 于是我们就想到了这样一个算法：如果当前查看的数小于curMax，它会挤掉所有栈顶比它大的数（因为这些都会是和它处于同一个chunk的数
     * 但是我们保留curMax仍然再放回栈里面去。这是因为curMax就是用来判定新数是否属于同一chunk的，我们需要时刻把它放在栈顶。
     */
    public int maxChunksToSorted(int[] arr) {
        Stack<Integer> sk = new Stack<>();
        int curMax = 0;
        for (int a : arr) {
            if (sk.size() == 0 || a >= sk.peek()) {
                sk.push(a);
                curMax = a;
            } else {
                while (sk.size() > 0 && sk.peek() > a) sk.pop();
                sk.push(curMax);
            }
        }
        return sk.size();
    }

    public int maxChunksToSorted2(int[] arr) {
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arr2);
        int res = 0;
        int s1 = 0, s2 = 0;
        for (int i = 0; i < arr.length; i++) {
            s1 += arr2[i];
            s2 += arr[i];
            if (s1 == s2) {
                res++;
                s1 = 0;
                s2 = 0;
            }
        }
        return res;
    }
}
