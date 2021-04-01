package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC1234ReplaceTheSubstringForBalancedString extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1234ReplaceTheSubstringForBalancedString();
        LC.balancedString("abcd");
    }

    private int char2int(char c) {
        switch (c) {
            case 'Q':
                return 0;
            case 'W':
                return 1;
            case 'E':
                return 2;
        }
        return 3;
    }

    /**
     * https://leetcode.com/problems/replace-the-substring-for-balanced-string/submissions/
     * 結果這題lee的解法沒看懂, 反而另一個解釋比較易懂
     * 這題不能直接用sliding window 來解 -> 要先計算總數, 才知道多出來的字元
     * -> 這樣才知道 sliding window 內要的計算條件就是包含到多出來的字元的多出來數目 -> 透過一層轉換來計算 sliding window的概念題目
     * 因為要換的是 substring -> 要換就換一串, 不能個別換 -> 要換的對象就是sliding window
     * 要找出最小的 sliding window(如果要找最大, 那就乾脆整個字串s換掉就好了, 因為是最大substring去換)
     * 1. 先計算s中每個字元總數, 然後把他們減去 balance size -> 短缺的count改為0, 多出來的count是差值
     * -> 這樣freq裡面存的, > 0 的都是要被包含在 sliding window(substring)裡面
     * 2. 計算完, 如果有字元超過balance, 那麼那個字元超過的數目就一定得在要被換的substring之中, 所以sliding window就可以開始計算
     * 3. sliding window i=0, j 一直往後擴增, 每擴增就減少該字元的出現count
     * -> 因為被包在sliding window 中的字元最終都會被換掉, 所以count 一定要減少
     * -> 我們不用計算減少要補給短缺的字元的方案, 因為事先count都剪去了balance size, 短缺的語意跟找sliding window無關了
     * 4. 檢查目前字元總計是否都 <= 0, 如果是
     * -> 當前sliding window是可以換成balance
     * -> 計算 min, 然後縮短 sliding window(把原本freq[i]++ -> 被包含的被拿出來, 所以 freq要補回來)
     * */
    public int balancedString(String s) {
        int[] freq = new int[4];
        int t = s.length() / 4;
        for (char c : s.toCharArray()) freq[char2int(c)]++;
        boolean equal = true;
        for (int i = 0; i < 4; i++) {
            if (freq[i] != t) equal = false;
            freq[i] = Math.max(0, freq[i] - t);
        }
        if (equal) return 0;

        int i = 0;
        int minLen = s.length();
        for (int j = 0; j < s.length(); j++) {
            freq[char2int(s.charAt(j))]--;
            while (fullfill(freq)) {
                minLen = Math.min(j - i + 1, minLen);
                freq[char2int(s.charAt(i))]++;
                i++;
            }
        }
        return minLen;
    }

    private boolean fullfill(int[] freq) {
        boolean r = true;
        for (int f : freq) r = r & f <= 0;
        return r;
    }
}
