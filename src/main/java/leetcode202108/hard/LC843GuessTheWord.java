package leetcode202108.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LC843GuessTheWord extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC843GuessTheWord();
    }

    interface Master {
        public int guess(String word);
    }

    /**
     * https://leetcode.com/problems/guess-the-word/discuss/133862/Random-Guess-and-Minimax-Guess-with-Comparison
     * 這題真的好難, 是一個考你策略思考, 就是說因為 wordlist 是隨機產生, 沒有一個演算法確保你一定都會 pass 所有 test case
     * (有可能生出一個 test case, 保證沒有驗算法可以讓你在 10次 guess 內找到答案)
     * 是要你想出一個策略, 讓大部分的 test case 你在 10次 guess 內盡可能猜到答案
     * <p>
     * 這邊提出的找出 secret 的辦法就是, 挑出一個 word 去 guess, 可能得到 guess = 3, 代表該 word 跟 secret exactly match 3 chars
     * 所以就是撈 wordlist 中跟 word match 3 chars 的 candidate 來進行下一輪 guess
     * (因為裡面除了包含 secret, 也包含了其他 3 chars match 但不是secret)
     * 過程中就會越來越少 -> 但這沒有保證 10次內會濾出 secret -> 所以怎麼挑出 word 來進行 guess 是關鍵 來達成最高機率通過
     * random select 有 70%機率 通過 random test cases
     * <p>
     * 透過去先計數所有candidate每個位置的字元的出現次數(因為每個candidate 長度都是 6),
     * 然後看哪個 candidate 拿到最多出現次數的字元總數score, 那麼就是比較接近的 candidate 可以拿來 guess
     * (因為如果 word x 的字元只有他有, 那他拿到的 score = 6, 那麼如果拿 word x 來 guess 只是更慢找到 secret)
     */
    public void findSecretWord(String[] wordlist, Master master) {
        for (int t = 0, x = 0; t < 10 && x < 6; t++) {
            int count[][] = new int[6][26], best = 0;
            for (String w : wordlist)
                for (int i = 0; i < 6; i++)
                    count[i][w.charAt(i) - 'a']++;
            String guess = wordlist[0];
            for (String w : wordlist) {
                int score = 0;
                for (int i = 0; i < 6; i++) {
                    score += count[i][w.charAt(i) - 'a'];
                }
                if(score > best) {
                    guess = w;
                    best = score;
                }
            }
            x = master.guess(guess);
            List<String> wordList2 = new ArrayList<>();
            for (String w : wordlist)
                if (matchs(guess, w) == x)
                    wordList2.add(w);
            wordlist = wordList2.toArray(new String[wordList2.size()]);
        }
    }

    private int matchs(String x, String y) {
        int m = 0;
        for (int i = 0; i < 6; i++) if (x.charAt(i) == y.charAt(i)) m++;
        return m;
    }
}
