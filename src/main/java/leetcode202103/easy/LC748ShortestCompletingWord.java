package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC748ShortestCompletingWord extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC748ShortestCompletingWord();
        LC.shortestCompletingWord("1s3 PSt", new String[]{"step", "steps", "stripe", "stepple"});
    }

    public String shortestCompletingWord(String licensePlate, String[] words) {
        String f = null;
        for (String w : words) {
            Map<Character, Integer> m = new HashMap<>();
            for (char c : licensePlate.toCharArray()) {
                char cs = Character.toLowerCase(c);
                if (Character.isAlphabetic(cs)) m.put(cs, m.getOrDefault(cs, 0) + 1);
            }
            for (char cw : w.toCharArray()) {
                char cws = Character.toLowerCase(cw);
                if (m.containsKey(cws)) {
                    Integer cwi = m.get(cws);
                    if (cwi == 1) m.remove(cws);
                    else m.put(cws, cwi - 1);
                }
            }
            if ((m.size() == 0 && f == null) || (m.size() == 0 && f.length() > w.length())) f = w;
        }
        return f;
    }
}
