package leetcode202211.contest;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.*;


public class WeeklyContest319 extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new WeeklyContest319();
        TreeNode rt = new TreeNode();
        TreeNode t1 = rt.buildTreeByLevel(new Integer[]{1, 4, 3, 7, 6, 8, 5, null, null, null, null, 9, null, 10}, 1);
        LC.minimumOperations(t1);
    }

    public double[] convertTemperature(double celsius) {
        return new double[]{celsius + 273.15D, celsius * 1.8D + 32D};
    }

    /**
     * 想不到這題是直觀 n*n 卡我好久...
     */
    public int subarrayLCM(int[] nums, int k) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            int lcm = nums[i];
            for (int j = i; j < n; j++) {
                lcm = lcm(nums[j], lcm);
                if (lcm > k) break;
                else if (lcm == k) res++;
            }
        }
        return res;
    }

    int lcm(int x, int y) {
        return x * y / gcd(x, y);
    }

    int gcd(int x, int y) {
        if (x == 0) return y;
        return gcd(y % x, x);
    }

    /**
     * 完全不知道要達成 mini swap 就是 cycle sort
     * https://leetcode.com/discuss/study-guide/1902662/cyclic-sort-very-important-and-less-known-pattern
     * cycle sort 為什麼是 minimum swap 解釋
     * 直觀來講, https://www.cnblogs.com/kkun/archive/2011/11/28/2266559.html
     * 待排数组[ 6 2 4 1 5 9 ]
     * 排完序后[ 1 2 4 5 6 9 ]
     * 数组索引[ 0 1 2 3 4 5 ]
     * <p>
     * 6应该出现在索引4的位置上,而它现在却在位置0上
     * -而待排数组索引4位置上的5应该出现在索引3的位置上
     * 同样的,待排数组索引3的位置是1
     * <p>
     * [ 6 5 1 ] 要換到 各自正確位置, 就是一個 cycle, cycle 換完大家都在正確位置上
     * https://stackoverflow.com/questions/48500301/correctness-of-minimum-number-of-swaps-to-sort-an-array
     * 如果 cycle 內有 n 個待換的, 所以要 n-1 swap, 上面有證明
     * 所以 cycle sort 是 minimum swap
     * <p>
     * 但我直接拿 unsorted , 去依照 sorted 來 swap 也不太對
     * https://leetcode.com/problems/minimum-number-of-operations-to-sort-a-binary-tree-by-level/discuss/2808850/Easy-Java-Solution
     * 之前做法 應該是沒做對 cycle sort
     * <p>
     * 但竟然直接 set 不是在正確位置上的元素到, 正確位置上的步驟 就是符合cycle sort 的 minimum swap
     * <p>
     * 參考 c++ 重做看看
     * https://leetcode.com/problems/minimum-number-of-operations-to-sort-a-binary-tree-by-level/discuss/2808869/Index-Array/1682016
     *
     * 關鍵在 int cycleSwap(List<Integer> unsorted, List<Integer> sorted) {
     * 1. 拿 sorted 來建立 map 確認每個值的正確 index
     * 2. unsorted 從 0 to n 檢查, 如果當前 i 得值不該在 i 上 , swap 到該在的位置
     * 然後再繼續檢查 i swap 後是不是正確值, 是的話就往下走, 不是繼續swap
     * 每次 swap 都是 count++, 結果就是答案
     */
    public int minimumOperations(TreeNode root) {
        int res = 0;
        Queue<TreeNode> bfs = new LinkedList<>();
        bfs.offer(root);
        while (bfs.size() > 0) {
            int s = bfs.size();
            List<Integer> unsorted = new ArrayList<>();
            for (int i = 0; i < s; i++) {
                TreeNode n = bfs.poll();
                unsorted.add(n.val);
                if (n.left != null) bfs.add(n.left);
                if (n.right != null) bfs.add(n.right);
            }
            List<Integer> sorted = new ArrayList<>(unsorted);
            Collections.sort(sorted);
            res += cycleSwap(unsorted, sorted);
        }
        return res;
    }

    int cycleSwap(List<Integer> unsorted, List<Integer> sorted) {
        int res = 0;
        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < sorted.size(); i++) idxMap.put(sorted.get(i), i);
        for (int i = 0; i < unsorted.size(); i++) {
            while (i != idxMap.get(unsorted.get(i))) {
                int correctPos = idxMap.get(unsorted.get(i));
                Collections.swap(unsorted, i, correctPos);
                res++;
            }
        }
        return res;
    }
}
