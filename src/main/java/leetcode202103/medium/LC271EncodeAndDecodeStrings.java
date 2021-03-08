package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC271EncodeAndDecodeStrings extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC271EncodeAndDecodeStrings();
    }

    /**
     * 我一直以為String 有ASCII 所有 characters 所有字元, 沒辦法用什麼字元當separator
     * https://leetcode.com/problems/encode-and-decode-strings/discuss/70412/AC-Java-Solution
     * 但看到答案才想通, 可以隨意選個 separator, 組合就是 encode string size + separator + string
     * 1. 組合起來的字串第一個separator 前面一定是長度, 接著就是 string
     * 因為decode不是拿 split(separator), 是根據長度來挖出字串, 所以無視字串中有什麼, 直接整串拿出來, 然後記得當下consume的 index i
     * 下一個 s.indexOf("/", i); 一定還是你當初encode的 separator, 就這樣一直consume下去
     * 我果然對於字串encode decode不熟
     */
    public String encode(List<String> strs) {
        StringBuilder b = new StringBuilder();
        for (String s : strs) b.append(s.length()).append("/").append(s);
        return b.toString();
    }

    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int slash = s.indexOf("/", i);
            int si = Integer.parseInt(s.substring(i, slash));
            i = slash + si + 1;
            res.add(s.substring(slash + 1, i));
        }
        return res;
    }
}
