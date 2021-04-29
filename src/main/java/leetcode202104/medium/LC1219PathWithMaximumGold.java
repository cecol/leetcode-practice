package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC1219PathWithMaximumGold extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1219PathWithMaximumGold();
    }

    /**
     * 蠻直觀的一題, 直接dfs走出所有可能 faster than 95.13%
     * */
    int res = 0;
    public int getMaximumGold(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                dfs(grid,0,i,j);
            }
        }
        return res;
    }
    private void dfs(int[][] grid, int cur, int i, int j){
        int m = grid.length, n = grid[0].length;
        if(i<0||j<0||i>=m||j>=n||grid[i][j] == 0) return;
        int mg = grid[i][j];
        res = Math.max(res, mg+cur);
        grid[i][j] = 0;
        dfs(grid,cur+mg,i+1,j);
        dfs(grid,cur+mg,i-1,j);
        dfs(grid,cur+mg,i,j+1);
        dfs(grid,cur+mg,i,j-1);
        grid[i][j] = mg;
    }
}
