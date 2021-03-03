package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC1078OccurrencesAfterBigram extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1078OccurrencesAfterBigram();
        LC.findOcurrences("alice is a good girl she is a good student", "a", "good");
    }

    public String[] findOcurrences(String text, String first, String second) {
        boolean a = false, b = false;
        List<String> res = new ArrayList<>();
        for (String t : text.split(" ")) {
            if (!a && !b) {
                a = t.equals(first);
            } else if (a && !b) {
                if (t.equals(second)) b = true;
                else a = t.equals(first);
            } else {
                res.add(t);
                a = t.equals(first);
                b = false;
            }
        }
        return res.toArray(new String[res.size()]);
    }
}
