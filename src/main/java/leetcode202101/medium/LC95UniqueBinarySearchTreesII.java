package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC95UniqueBinarySearchTreesII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC95UniqueBinarySearchTreesII();
        var s = LC.generateTrees(3);
    }

    /**
     * https://leetcode.com/problems/unique-binary-search-trees-ii/discuss/31508/Divide-and-conquer.-F(i)-G(i-1)-*-G(n-i)
     * 這題其實是DP 96的把所有可能的tree都畫出來
     * 但我不知道怎透過96的過程把tree建立出來直到看到這個解法才恍然大悟
     *
     * 基本概念就是96的i from 1 to n
     * 分別遞迴下去建立各種leftSubs & rightSubs
     * 然後雙重迴圈分別取出每一個leftSub與rightSub交錯與當前的root i 建立回真正的tree
     * */
    public List<TreeNode> generateTrees(int n) {
        return generateSubTrees(1, n);
    }

    private List<TreeNode> generateSubTrees(int s, int e) {
        List<TreeNode> res = new ArrayList<>();
        if (s > e) {
            res.add(null);
            return res;
        }
        for (int i = s; i <= e; i++) {
            List<TreeNode> leftSub = generateSubTrees(s, i - 1);
            List<TreeNode> rightSub = generateSubTrees(i + 1, e);
            for(TreeNode left:leftSub) {
                for(TreeNode right:rightSub) {
                    TreeNode r = new TreeNode(i);
                    r.left=left;
                    r.right=right;
                    res.add(r);
                }
            }

        }
        return res;
    }
}
