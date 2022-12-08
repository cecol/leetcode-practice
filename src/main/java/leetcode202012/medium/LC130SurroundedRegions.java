package leetcode202012.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC130SurroundedRegions extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC130SurroundedRegions();
    LC.solve(new char[][]{
        {'X', 'X', 'X', 'X'},
        {'X', 'O', 'O', 'X'},
        {'X', 'X', 'O', 'X'},
        {'X', 'O', 'X', 'X'}
    });
  }

  /**
   * https://leetcode.com/problems/surrounded-regions/discuss/41633/Java-DFS-%2B-boundary-cell-turning-solution-simple-and-clean-code-commented.
   * https://leetcode.com/problems/surrounded-regions/discuss/163363/Java-DFS-with-Explanations
   * Logical Thinking
   * We aim to set all O's which doesn't locate at borders or connect to O at borders to X.
   * We mark all O's at borders and apply DFS at each O at boarders to mark all O's connected to it. The un-marked O's ought to be set X.
   *
   * Trick
   * We search for invalid candidates (and exclude them) rather than search for valid candidates.
   *
   * 2022/12/6 回來看還是卡住, 題意還是沒看好！！
   * 關鍵是以為只是外圍 O 換成 X, 但其實是只要 4 週都被包圍, 全部都是要換成 X !!
   * A region is captured by flipping [all] 'O's into 'X's in that surrounded region.
   *
   * 所以這樣就很簡單, 只有邊界的 O 跟接連不斷的 O 不能被換成 X, 其他都可以
   * 所以反過來先 dfs 標記 邊界的 O 成為 *,
   * 標完後 全部走一遍
   * if '*' -> 'O'
   * else if 'O' -> 'X'
   */
  public void solve(char[][] board) {
    if (board.length < 2 || board[0].length < 2) return;
    int m = board.length;
    int n = board[0].length;
    for (int i = 0; i < m; i++) {
      if (board[i][0] == 'O') dfs(board, i, 0);
      if (board[i][n - 1] == 'O') dfs(board, i, n - 1);
    }
    for (int j = 0; j < n; j++) {
      if (board[0][j] == 'O') dfs(board, 0, j);
      if (board[m - 1][j] == 'O') dfs(board, m - 1, j);
    }
    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++) {
        if (board[i][j] == '*') board[i][j] = 'O';
        else if (board[i][j] == 'O') board[i][j] = 'X';
      }
  }

  public void dfs(char[][] board, int i, int j) {
    int m = board.length;
    int n = board[0].length;
    if (i < 0 || i >= m || j < 0 || j >= n) return;
    if (board[i][j] == 'O') board[i][j] = '*';
    if (i > 1 && board[i - 1][j] == 'O') dfs(board, i - 1, j);
    if (i < m - 1 && board[i + 1][j] == 'O') dfs(board, i + 1, j);
    if (j > 1 && board[i][j - 1] == 'O') dfs(board, i, j - 1);
    if (j < n - 1 && board[i][j + 1] == 'O') dfs(board, i, j + 1);
  }
}
