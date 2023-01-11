package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC527WordAbbreviation extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC527WordAbbreviation();
    }

    /**
     * https://leetcode.com/problems/word-abbreviation/solutions/99793/clean-java-recursive-solution-beats-99-using-hashmap/
     * 沒想到有遞迴可以解的的麼乾淨
     *
     * 1. 準備一個最終結果 - Map<String, String> wordToAbbr = new HashMap<>();
     * - dfs 下去遞迴,
     * - dfs 每一層都帶入
     * -     prefix 現在嘗試 Abbreviation 第幾次
     * -     dfs 內建 Map<String, List<String>> abbrToWords 看當前 abbr 是有沒有重複
     * -     Map<String, String> wordToAbbr, 如果當前 dfs 得到的 abbr 是獨一無二的 就可以加入
     * -     Map<String, String> wordToAbbr, 如果當前 dfs 得到的 abbr 是有重複
     * -        dfs(wordToAbbr, prefix + 1, en.getValue()); 重複的字, 再加一層 prefix 下去 dfs 看看會不會再重複
     * -     如果當前 dfs 已經是不同 key in abbrToWords, 就保證 一定跟其他人不一樣
     * -     因為 大家 prefix 都不一樣
     * -     dfs 下一層 prefix+1, 也只會更不一樣, 因為 prefix 還是不一樣
     *
     * - String getAbbr(String s, int prefix)
     * 要檢查是否 abbr 有更短 如果沒有就算了 - prefix + 2 >= s.length()
     * */
    public List<String> wordsAbbreviation(List<String> words) {
        Map<String, String> wordToAbbr = new HashMap<>();
        dfs(wordToAbbr, 1, words);
        List<String> res = new ArrayList<>();
        for (String s : words) res.add(wordToAbbr.get(s));
        return res;
    }

    void dfs(Map<String, String> wordToAbbr, int prefix, List<String> words) {
        Map<String, List<String>> abbrToWords = new HashMap<>();
        for (String s : words) {
            String abbr = getAbbr(s, prefix);
            if (!abbrToWords.containsKey(abbr)) abbrToWords.put(abbr, new ArrayList<>());
            abbrToWords.get(abbr).add(s);
        }
        for (Map.Entry<String, List<String>> en : abbrToWords.entrySet()) {
            if (en.getValue().size() == 1) wordToAbbr.put(en.getValue().get(0), en.getKey());
            else dfs(wordToAbbr, prefix + 1, en.getValue());
        }
    }

    String getAbbr(String s, int prefix) {
        return prefix + 2 >= s.length() ? s : s.substring(0, prefix) + (s.length() - prefix - 1) + s.charAt(s.length() - 1);
    }
}
