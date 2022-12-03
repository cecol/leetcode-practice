package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Stack;

public class LC1944NumberOfVisiblePeopleInAQueue extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1944NumberOfVisiblePeopleInAQueue();
    }

    /**
     * https://leetcode.com/problems/number-of-visible-people-in-a-queue/discuss/1359707/JavaC%2B%2BPython-Stack-Solution-Next-Greater-Element
     * 這題看起來很不直觀, 但其實想想後沒這麼難
     * A[i] 只要遇到 nestGreater 後面就都看不到
     * 所以 A[i] 遇到 nestGreater 就是可以 pop 並且 +1 看到的人, 因為他看不到更後面比他矮了
     *
     * 從 0 to n-1
     * 所以看起來是維持一個 monotonic decrease stack
     * 我們擺入 index i, 每當 stack.size() > 0, 看看當前 A[i] 是否是 stack.top 的 nestGreater
     * 如果是 就是 res[stack.pop]++
     *
     * 然後 stack.push(i),
     * 可是在 stack.push(i) 前如果 stack.size() > 0, 等於說 stack.top() 是看得到 i, 所以 res[stack.top()]++
     * 另一方面, 剛剛被 pop 掉的,
     * 比如說 [5, 3, 1, 4, 6]
     * stack [5, 3, 1], 對於 5 來說只看到 3, 看不到 1, 所以當遇到 4, 把 1 pop 掉, 對 5 也沒影響
     * 5 看到 3, 在 push(3) 時候就 ++ 了
     * 5 看到 4, 在 push(4) 時候就 ++ 了
     * 直到遇到 6, 5 被 pop 且 ++
     * */
    public int[] canSeePersonsCount(int[] A) {
        int n = A.length, res[] = new int[n];
        Stack<Integer> sk = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (sk.size() > 0 && A[sk.peek()] <= A[i]) res[sk.pop()]++;
            if(!sk.isEmpty()) res[sk.peek()]++;
            sk.push(i);
        }
        return res;
    }
}
