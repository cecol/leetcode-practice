package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC652FindDuplicateSubtrees extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC652FindDuplicateSubtrees();
        var s = LC.findDuplicateSubtrees(null);
    }

    /**
     * https://leetcode.com/problems/find-duplicate-subtrees/discuss/106055/C%2B%2B-Java-Clean-Code-with-Explanation
     * 這題應該要用postorder來看待, 因為一定是從leaf 來回推找回來
     * 只有postorder 才是先把左右走完 -> 優先看到葉子再慢慢長回來
     * 但這題的postorder概念是建立map的時機點
     *
     * 下面這段看起來是inorder
     * String s = "(" + serialize(n.left,map) + n.val + serialize(n.right, map) + ")";
     * 是指說 serialize 是用inorder
     * 但是把建立出來的serialize結果用postorder加入到map
     *
     * 要找出兩個tree 是否依樣 就直接看serialize結果是否依樣,
     * 所以先把左右子樹都下去serialize -> 然後回來之後看看map有無含自己的serialize
     * 1. 有 -> 代表出現過重複, 把自己也加入紀錄
     * 2. 沒有 -> 代表還沒出現(可能這層結束後的更上層的遞迴會踩到) 先把自己加入
     *
     * */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, List<TreeNode>> map = new HashMap<>();
        List<TreeNode> res = new ArrayList<>();
        serialize(root, map);
        for(List<TreeNode> group: map.values()) if(group.size() > 1) res.add(group.get(0));
        return res;
    }

    private String serialize(TreeNode n, Map<String, List<TreeNode>> map) {
        if(n == null) return "";
        String s = "(" + serialize(n.left,map) + n.val + serialize(n.right, map) + ")";
        if(!map.containsKey(s)) map.put(s, new ArrayList<>());
        map.get(s).add(n);
        return s;
    }
}
