package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC1157OnlineMajorityElementInSubarray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1157OnlineMajorityElementInSubarray();
    }

    /**
     * https://leetcode.com/problems/online-majority-element-in-subarray/discuss/466221/JavaSegment-Tree-Easy-to-understand-beat-99
     * 用Segment Tree 來記載 該segment的 Majority
     * 每個 SegmentTreeNode 都有個 Pair, 裡面有 val, vote
     * https://leetcode.com/problems/online-majority-element-in-subarray/discuss/466221/JavaSegment-Tree-Easy-to-understand-beat-99/455572
     * val = Majority candidate
     * vote = Moore's voting algorithm
     * 這邊也有更細節解釋 https://github.com/wisdompeak/LeetCode/tree/master/Binary_Search/1157.Online-Majority-Element-In-Subarray
     * freqDiff(Pair.vote)表示这个majority candidate在该区间的频次与其他元素的频次差的“最大可能值”
     * <p>
     * Ex: 假设区间d，has left, right
     * left区间中的majority是val1，它的频次比其他元素多了diff1
     * right区间中的majority是val2，它的频次比其他元素多了diff2
     * 1. d的majority一定只能是val1和val2中的一个
     * 2. 我们对于 majority 的定义是区间内频次大于50%的元素,(majority.vote 減去其他 vote 還是 > 0)
     * 如果任何一个元素在两个子区间中都不占50%多数，那么在整个大区间中肯定也不会占50%多数
     * 1. 如果val1 == val2，那么毫无悬念，区间d的majority就是val1（或者val2）。它在整个区间里的频次优势或达到 vote = left.vote + right.vote
     * 如果 va1l != val2，我们需要两个中间选择一个。
     * (1) 如果left.vote > right.vote，则val1相比于val2更有可能是整个区间的majority -> 因為 left.vote 減去 right.vote, 還是 > 0 => Moore's voting algorithm
     * 但是注意，不一定表示val1就一定真的是majority，因为这个区间可能根本没有majority，所以val1只是best majority candidate。
     * Ex left:[2,2,3,3], val = 3, vote = 0, right:[2,2,4,4], val = 2, vote = 0, 就是一個案例
     * <p>
     * 那么对于val1而言，它在d区间内的频率优势如何计算呢？
     * 我们取最大可能值：left.vote - right.vote
     * (2) 反之，left.vote < right.vote，那么我们就认为val2是整个区间的best majority candidate，d.vote = right.vote - left.vote.
     *
     * 在 query 裏面
     * 1. 如果找到的 candidate.vote >= threshold 就可以直接回傳 因為等於 candidate.vote 減去其他人還 >= threshold
     * 2. 如果 candidate.vote < threshold, 代表要找回減去其他人vote 的數值來加回來看是否真的 >= threshold
     * 所以要一個 hashmap 來記載每個 candidate 位置, 然後用 start, end 下去找
     * 落在left - right區間, 如果 right - left + 1 >= threshold 也是可以, 因為區間大小就包含了 candidate 拿去減別人的 vote
     *
     * 所以
     * 1. Segment tree -> 找出 candidate
     * 2. hashmap 協助計算 candidate 是否真的有加總 vote 數 >= threshold
     */
    int[] arr;
    SegmentTreeNode rt;
    Map<Integer, List<Integer>> idxMap = new HashMap<>();

    public void MajorityChecker(int[] arr) {
        this.arr = arr;
        rt = buildTree(0, arr.length - 1, arr);
        for (int i = 0; i < arr.length; i++) {
            if (idxMap.get(arr[i]) == null) idxMap.put(arr[i], new ArrayList<>());
            idxMap.get(arr[i]).add(i);
        }
    }

    public int query(int left, int right, int threshold) {
        Pair candidate = queryTree(rt, left, right);
        if (candidate.vote >= threshold) return candidate.val;

        int leftIdx = Collections.binarySearch(idxMap.get(candidate.val), left);
        if (leftIdx < 0) leftIdx = -leftIdx - 1;

        int rightIdx = Collections.binarySearch(idxMap.get(candidate.val), right);
        if (rightIdx < 0) rightIdx = -rightIdx - 2;

        if (rightIdx - leftIdx + 1 >= threshold) return candidate.val;

        return -1;
    }

    Pair queryTree(SegmentTreeNode rt, int l, int r) {
        if (l <= rt.start && rt.end <= r) return rt.pair;
        Pair res = new Pair(0, 0);
        int mid = rt.start + (rt.end - rt.start) / 2;
        if (l <= mid) res = mergePair(res, queryTree(rt.left, l, r));
        if (mid < r) res = mergePair(res, queryTree(rt.right, l, r));
        return res;
    }


    SegmentTreeNode buildTree(int l, int r, int[] arr) {
        if (l == r) return new SegmentTreeNode(l, r, new Pair(arr[l], 1));
        SegmentTreeNode rt = new SegmentTreeNode(l, r, null);
        int mid = l + (r - l) / 2;
        rt.left = buildTree(l, mid, arr);
        rt.right = buildTree(mid + 1, r, arr);
        rt.pair = mergePair(rt.left.pair, rt.right.pair);
        return rt;
    }

    Pair mergePair(Pair lp, Pair rp) {
        if (lp.val == rp.val) return new Pair(lp.val, lp.vote + rp.vote);
        if (lp.vote > rp.vote) return new Pair(lp.val, lp.vote - rp.vote);
        return new Pair(rp.val, rp.vote - lp.vote);
    }

    class SegmentTreeNode {
        int start, end;
        Pair pair;
        SegmentTreeNode left, right;

        SegmentTreeNode(int s, int e, Pair p) {
            start = s;
            end = e;
            pair = p;
        }
    }

    class Pair {
        int val, vote;

        Pair(int v, int vote) {
            val = v;
            this.vote = vote;
        }
    }
}
