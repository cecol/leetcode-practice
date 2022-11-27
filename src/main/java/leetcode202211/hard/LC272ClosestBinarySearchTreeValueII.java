package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.*;

public class LC272ClosestBinarySearchTreeValueII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC272ClosestBinarySearchTreeValueII();
    }


    /**
     * 這題我有想到用 Deque, 但沒有用好,
     * 這題跟 LC658FindKClosestElements 幾乎一模一樣, 只是 sorted array 變成了 BST
     * 直觀 O(N) 解法是
     * https://leetcode.com/problems/closest-binary-search-tree-value-ii/discuss/186606/Deque-Solution-Java.-Definitely-easy-to-understand.
     * 就是把BST 塞入一個 Deque, 最後如果 Deque.size() > k, 就左右距離 target 遠的開始刪除
     * 不用在 inorder 過程中處理 Deque, 出來後再處理 Deque 刪減成 k 個
     * 在 inorder 過程中處理 Deque 也不見得 time complexity < O(N)
     * <p>
     * 這題是鎖題, 官方解答有提到幾件事
     * https://leetcode.com/problems/closest-binary-search-tree-value-ii/solution/
     * 1. Solution 1 把 BST 塞入 List, 然後用
     * Collections.sort(list, (x, y) -> Math.abs((double) x - target) < Math.abs((double) y - target) ? -1 : 1);
     * 然後取出 list 前 k 個
     * time complexity O(N log N)
     * Space complexity: O(N)
     * <p>
     * 2. Facebook-friendly - 想不到 FB 有喜歡的解法 -> 目的就是想考你 heap 操作 不是單純只是要解答
     * 也是一樣把 inorder BST 塞入 heap, 過程中 heap 爆了 就 heap.poll()
     * heap 裏面是 最遠的放在最上面, 最近的最下面
     * Queue<Integer> heap = new PriorityQueue<>((o1, o2) -> Math.abs(o1 - target) > Math.abs(o2 - target) ? -1 : 1);
     * 最後 heap 內就是 close k 個
     * time complexity O(N log k), 可能可以因為 inorder 提早結束 速度加快 但 worst case 還是
     * Space complexity: O(k+H) -> heap of k elements and the recursion stack of the tree height.
     * <p>
     * <p>
     * 3. Google-friendly, quickselect, O(N) time -> 目的就是想考你 quickselect 操作 不是單純只是要解答
     * Google guys usually prefer the best-time solutions, well-structured clean skeleton,
     * 務必記得 quickselect 仍可能 worst case 是 O(N ^ 2)
     * 要解 worst case 可以用
     * 這邊實作再選 pivot 時候特地加上 random 來降低 worst case
     */
    List<Integer> nums;
    double target;

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        nums = new ArrayList();
        this.target = target;
        inorder(root, nums);
        quickSelect(0, nums.size() - 1, k);
        return nums.subList(0, k);
    }

    void quickSelect(int left, int right, int k) {
        if (left >= right) return;
        Random randomNum = new Random();
        int pivot = left + randomNum.nextInt(right - left);
        pivot = partition(left, right, pivot);
        if (k == pivot) return;
        else if (k < pivot) quickSelect(left, pivot - 1, k);
        quickSelect(pivot + 1, right, k);
    }

    int partition(int left, int right, int pivot) {
        double pivotDist = Math.abs(nums.get(pivot) - target);

        Collections.swap(nums, pivot, right);
        int storeIndex = left;

        for (int i = left; i <= right; i++) {
            if (Math.abs(nums.get(i) - target) < pivotDist) {
                Collections.swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        Collections.swap(nums, storeIndex, right);
        return storeIndex;
    }

    void inorder(TreeNode rt, List<Integer> nums) {
        if (rt == null) return;
        inorder(rt.left, nums);
        nums.add(rt.val);
        inorder(rt.right, nums);
    }

    public List<Integer> closestKValuesDequq(TreeNode root, double target, int k) {
        Deque<Integer> dq = new LinkedList<>();
        inorder2(root, dq);
        while (dq.size() > k) {
            if (Math.abs(dq.peekLast() - target) < Math.abs(dq.peekFirst() - target)) dq.pollFirst();
            else dq.pollLast();
        }
        return new ArrayList<>(dq);
    }

    void inorder2(TreeNode rt, Deque<Integer> dq) {
        if (rt == null) return;
        inorder2(rt.left, dq);
        dq.add(rt.val);
        inorder2(rt.right, dq);
    }
}
