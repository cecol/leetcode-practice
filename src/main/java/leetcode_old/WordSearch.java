package leetcode_old;

import java.util.HashMap;
import java.util.Map;

public class WordSearch {
    public static void main(String[] args) {
        char[][] board1 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word1 = "ABCCED";
        System.out.println(exist(board1, word1));
        char[][] board2 = {{'A', 'A'}};
        String word2 = "AAA";
        System.out.println(exist(board2, word2));
        char[][] board3 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}};
        String word3 = "ABCESEEEFS";
        System.out.println(exist(board3, word3));

    }

    public static boolean exist(char[][] board, String word) {
        boolean result = false;
        if (word.length() == 0 || board.length == 0) return false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    Map<String, Boolean> record = new HashMap<String, Boolean>();
                    record.put(i + "," + j, false);
                    result = goThrough(i, j, 0, board, word, record);
                }
                if (result) return true;
            }
        }

        return result;
    }

    public static boolean goThrough(int x,
                                    int y,
                                    int p,
                                    char[][] board,
                                    String word,
                                    Map<String, Boolean> path) {
        if (p == word.length() - 1) return true;
        else {
            String k = x + "," + y;
            Integer x1 = x + 1; // ->
            Integer x2 = x - 1; // <-
            Integer y1 = y + 1; // ↑
            Integer y2 = y - 1; // ↓
            if (y1 < board[0].length &&
                    board[x][y1] == word.charAt(p + 1) &&
                    path.getOrDefault(x + "," + y1, true)) {
                path.put(x + "," + y1, false);
                boolean r1 = goThrough(x, y1, p + 1, board, word, path);
                if (r1) return true;
            }
            if (y2 >= 0 &&
                    board[x][y2] == word.charAt(p + 1) &&
                    path.getOrDefault(x + "," + y2, true)) {
                path.put(x + "," + y2, false);
                boolean r2 = goThrough(x, y2, p + 1, board, word, path);
                if (r2) return true;
            }
            if (x1 < board.length &&
                    board[x1][y] == word.charAt(p + 1) &&
                    path.getOrDefault(x1 + "," + y, true)) {
                path.put(x1 + "," + y, false);
                boolean r3 = goThrough(x1, y, p + 1, board, word, path);
                if (r3) return true;
            }
            if (x2 >= 0 &&
                    board[x2][y] == word.charAt(p + 1) &&
                    path.getOrDefault(x2 + "," + y, true)) {
                path.put(x2 + "," + y, false);
                boolean r4 = goThrough(x2, y, p + 1, board, word, path);
                if (r4) return true;
            }
            path.remove(k);
            return false;
        }
    }
}
