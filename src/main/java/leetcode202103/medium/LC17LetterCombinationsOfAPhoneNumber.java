package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC17LetterCombinationsOfAPhoneNumber extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC17LetterCombinationsOfAPhoneNumber();
        LC.letterCombinations("23");
    }


    Map<Integer, List<Character>> m = Map.of(
            2, List.of('a', 'b', 'c'),
            3, List.of('d', 'e', 'f'),
            4, List.of('g', 'h', 'i'),
            5, List.of('j', 'k', 'l'),
            6, List.of('m', 'n', 'o'),
            7, List.of('p', 'q', 'r', 's'),
            8, List.of('t', 'u', 'v'),
            9, List.of('w', 'x', 'y', 'z')
    );

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) return res;
        per(res, digits, 0, new StringBuilder());
        log.debug("{}", res);
        return res;
    }

    private void per(List<String> res, String digits, int i, StringBuilder sb) {
        if (i == digits.length()) {
            res.add(sb.toString());
        } else {
            Integer c = Integer.parseInt(Character.toString(digits.charAt(i)));
            for (Character cc : m.get(c)) {
                sb.append(cc);
                per(res, digits, i + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }


}
