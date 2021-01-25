package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LC655PrintBinaryTree extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC655PrintBinaryTree();
    LC.printTree(null);
  }

  public List<List<String>> printTree(TreeNode root) {
    List<List<String>> res = new LinkedList<>();
    if (root == null) return res;
    int rows = getH(root);
    int columns = (int) (Math.pow(2, rows) - 1);
    List<String> row = new ArrayList<>();
    for (int i = 0; i < columns; i++) row.add("");
    for (int i = 0; i < rows; i++) res.add(new ArrayList<>(row));
    populate(root, res, 0, rows, 0, columns - 1);
    return res;
  }

  private int getH(TreeNode r) {
    if (r == null) return 0;
    return 1 + Math.max(getH(r.left), getH(r.right));
  }

  private void populate(TreeNode r, List<List<String>> res, int row, int totalRows, int i, int j) {
    if (row == totalRows || r == null) return;
    res.get(row).set((i + j) / 2, Integer.toString(r.val));
    populate(r.left, res, row + 1, totalRows, i, (i + j) / 2 - 1);
    populate(r.right, res, row + 1, totalRows, (i + j) / 2 + 1, j);
  }
}
