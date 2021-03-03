package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC884UncommonWordsFromTwoSentences extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC884UncommonWordsFromTwoSentences();
        LC.uncommonFromSentences("this apple is sweet", "this apple is sour");
    }

    public String[] uncommonFromSentences(String A, String B) {
        Map<String, Integer> cc = new HashMap<>();
        for (String s : A.split(" ")) {
            cc.put(s, cc.getOrDefault(s, 0) + 1);
        }
        for (String s : B.split(" ")) {
            cc.put(s, cc.getOrDefault(s, 0) + 1);
        }
        List<String> res = new ArrayList<>();
        for (String k : cc.keySet()) if (cc.get(k) == 1) res.add(k);
        return res.toArray(new String[res.size()]);
    }
}
