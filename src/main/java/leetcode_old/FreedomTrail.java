package leetcode_old;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/freedom-trail/#/description
 * dynamic programming problem but could Limit time exceed
 * another solution from https://discuss.leetcode.com/topic/81699/java-clear-solution-dfs-memoization
 */

public class FreedomTrail {
    public static void main(String[] args) {
        String[] t1 = {"godding", "godding"};
        String[] t2 = {"ababcab", "acbaacba"};
        String[] t3 = {"iotfo", "fioot"};
        String[] t4 = {"godding", "gd"};
        String[] t5 = {"edcba", "abcde"};
        String[] t6 = {"pqwcx", "cpqwx"};
        String[] t7 = {"tawzu", "zzzwwwuuutttaaa"};

        String[][] t = {t7};
        for (int i = 0; i < t.length; i++) {
            long startPoint = System.currentTimeMillis();
            int result = findRotateSteps(t[i][0], t[i][1]);
            System.out.println("test " + (i + 1) + " result:" + result + " cost:" + (System.currentTimeMillis() - startPoint));
        }
    }


    static Map<String, Map<Integer, Integer>> memo;

    public static int findRotateSteps(String ring, String key) {
        memo = new HashMap<>();
        return dfs(ring, key, 0);
    }

    private static int findPos(String ring, char ch) { // find first occurrence clockwise
        return ring.indexOf(ch);
    }

    private static int findBackPos(String ring, char ch) { //find first occurrence  anti-clockwise
        if (ring.charAt(0) == ch) return 0;
        for (int i = ring.length() - 1; i > 0; i--) {
            if (ring.charAt(i) == ch) return i;
        }
        return 0;
    }

    private static int dfs(String ring, String key, int i) {
        if (i == key.length()) return 0;
        int res = 0;
        char ch = key.charAt(i);
        if (memo.containsKey(ring) && memo.get(ring).containsKey(i)) return memo.get(ring).get(i);
        int f = findPos(ring, ch);
        int b = findBackPos(ring, ch);
        int forward = 1 + f + dfs(ring.substring(f) + ring.substring(0, f), key, i + 1);
        int back = 1 + ring.length() - b + dfs(ring.substring(b) + ring.substring(0, b), key, i + 1);
        res = Math.min(forward, back);
        Map<Integer, Integer> ans = memo.getOrDefault(ring, new HashMap<>());
        ans.put(i, res);
        memo.put(ring, ans);
        return res;
    }

    //my original dp solution, but LTE a lots
//    public static int findRotateSteps(String ring, String key) {
//        if (key == null || key.length() == 0) return 0;
//        int leaves = (new Double(Math.pow(2, key.length()))).intValue();
//        String[] mems = new String[leaves];
//        int[] movec = new int[leaves];
//        char[] keyr = key.toCharArray();
//
//        for (int i = 0; i < mems.length; i++) {
//            mems[i] = ring;
//            movec[i] = 0;
//        }
//
//        for (int i = 0; i < keyr.length; i++) {
//            int r = new Double(Math.pow(2, i + 1)).intValue();
//            String[] intermems = new String[r];
//            int[] intermoves = new int[r];
//            System.out.println("i:" + i + " round:" + r);
//            for (int j = 0; j < r; j++) {
//                int m = 0;
//                int p = mems[j / 2].indexOf(keyr[i]);
//                if (j % 2 == 0) {
//                    m = -p;
//                    intermoves[j] = Math.abs(m) + movec[j / 2];
//                    intermems[j] = rotate(mems[j / 2], m);
//                } else {
//                    m = p == 0 ? 0 : (mems[j / 2].length() - mems[j / 2].lastIndexOf(keyr[i]));
//                    intermoves[j] = m + movec[j / 2];
//                    intermems[j] = rotate(mems[j / 2], m);
//                }
//
//                System.out.println(" j:" + j + " m:" + m + " mems:" + mems[j / 2] + " => intermems:" + intermems[j]
//                        + " movec:" + movec[j / 2] + " => intermoves:" + intermoves[j]);
//            }
//
//
//            for (int j = 0; j < r; j++) {
//                mems[j] = intermems[j];
//                movec[j] = intermoves[j];
//            }
//        }
//
//        int max = movec[0];
//        for (int i = 0; i < movec.length; i++) {
//            System.out.println("move " + i + ": " + movec[i]);
//            max = Math.min(max, movec[i]);
//        }
//        return max + key.length();
//    }
//
//
//    public static String rotate(String ring, int d) {
//        char[] rotated = new char[ring.length()];
//        if (d == 0) return ring;
//        for (int i = 0; i < ring.length(); i++) {
//            int m = i + d;
//            if (m >= ring.length()) {
//                m = m % ring.length();
//            }
//            if (m < 0) {
//                m += ring.length();
//            }
//            rotated[m] = ring.charAt(i);
//        }
//        return String.valueOf(rotated);
//    }
}
