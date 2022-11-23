package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC331VerifyPreorderSerializationOfABinaryTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/discuss/1426956/Python3Java-Easy-Solution-Explained-in-Detail-or-O(1)-Space
     * 每想到是用 slot 觀點來看待這題
     * 1. null node 只會消耗一個 slot
     * 2. non-null node 會消耗一個 slot, 但也會供給 2個 slots (兩個 childs, 儘管他們可能是 null)
     *
     * 一開始 slots = 1 因為 for root
     * https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/discuss/1426956/Python3Java-Easy-Solution-Explained-in-Detail-or-O(1)-Space/1062180
     * 後續如果看到
     * 1. null node -> n.equals("#") -> slots--
     * 2. non-null node -> slots++ (加 2 減 1)
     *
     * 如果過程中 slots 歸 0 代表太多 null node, 所以後續的 node 也沒地方擺了
     * 最後如果 slots != 0, 代表給的 preorder 也不完整, 給的 null node 不足
     * */
    public boolean isValidSerialization(String pre) {
        String[] nodes = pre.split(",");
        int slots = 1;
        for (String n : nodes) {
            if(slots <= 0) return false;
            if(n.equals("#")) slots--;
            else slots++;
        }
        return slots == 0;
    }
}
