package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC650_2KeysKeyboard extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC650_2KeysKeyboard();
        var s = LC.minSteps(20);
    }

    /**
     *  https://leetcode.com/problems/2-keys-keyboard/discuss/105899/Java-DP-Solution
     *
     */
    public int minSteps(int n) {
        int[] s = new int[n + 1];
        for (int i = 2; i < s.length; i++) {
            s[i] = i;//default cost -> copy 1 A, then continually paste until n
            for (int j = i - 1; j > 1; j--) {
                // if sequence of length 'j' can be pasted multiple times to get length 'i' sequence
                if (i % j == 0) {
                    // we just need to paste sequence j (i/j - 1) times, hence additional (i/j) times since we need to copy it first as well.
                    // we don't need checking any smaller length sequences
                    // s[15] = s[5] + 1 copy + 2 paste => i/j-1(paste)+1(copy) => i/j
                    // 15/5 == 3 => 1 copy, 2 paste
                    s[i] = s[j] + i / j;
                    break;
                }
            }
        }
        log.debug("s: {}", s);
        return s[n];
    }
}
