package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class LC489RobotRoomCleaner extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC489RobotRoomCleaner();
    }

    interface Robot {
        // Returns true if the cell in front is open and robot moves into the cell.
        // Returns false if the cell in front is blocked and robot stays in the current cell.
        public boolean move();


        // Robot will stay in the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        public void turnLeft();

        public void turnRight();


        // Clean the current cell.
        public void clean();
    }

    /**
     * https://leetcode.com/problems/robot-room-cleaner/solutions/265763/robot-room-cleaner/
     * 這題有幾個關鍵知識
     * 1. Maze-solving algorithm - https://en.wikipedia.org/wiki/Maze-solving_algorithm#Wall_follower
     * 既然是 right-hand rule, 所以 int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {0, -1}}; 很關鍵
     * 不能隨便亂排, 得真的是 連續向右轉的 dir,
     * 2. 不要管 robot 起點在哪, 都當作 0,0 起點, 配合一個 Set<String> visited 來記錄走過的
     * 3. dfs 內, 看當前 dfs 進來時候的 dir, 然後
     * - 確認能往前走 (not visited + move() = true), 往前走 dfs 下去
     * - 不能往前走 or dfs 回來,
     * -     回頭一步, turnRight, turnRight, move, turnRight, turnRight
     * -     dir = (dir+1)%4
     * -     換下一個方向
     * */
    public void cleanRoom(Robot robot) {
        Set<String> visited = new HashSet<>();
        dfs(robot, visited, 0, 0, 0);
    }

    void dfs(Robot robot, Set<String> visited, int x, int y, int dir) {
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {0, -1}};
        visited.add(x + "," + y);
        robot.clean();
        for (int i = 0; i < 4; i++) {
            int xx = x + dirs[dir][0];
            int yy = y + dirs[dir][1];

            if (!visited.contains(xx + "," + yy)) {
                boolean open = robot.move();
                if (open) {
                    dfs(robot, visited, xx, yy, dir);

                    robot.turnRight();
                    robot.turnRight();
                    robot.move();
                    robot.turnRight();
                    robot.turnRight();
                }
            }
            robot.turnRight();
            dir = (dir + 1) % 4;
        }
    }
}
