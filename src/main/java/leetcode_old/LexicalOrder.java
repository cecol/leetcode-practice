package leetcode_old;

import java.util.ArrayList;

public class LexicalOrder {
    public static void main(String[] args) {
        ArrayList<Integer> r = lexicalOrder(5000);
        for (Integer i : r) {
            System.out.print(i + " ");
        }
    }

    public static ArrayList<Integer> lexicalOrder(int n) {
        ArrayList<Integer> result = new ArrayList<>();
        dfs(1, 9, n, result);
        return result;
    }

    private static void dfs(int start, int end, int n, ArrayList<Integer> result) {
        for (int i = start; i <= end && i <= n; i++) {
            result.add(i);
            dfs(i * 10, i * 10 + 9, n, result);
        }
    }
}
