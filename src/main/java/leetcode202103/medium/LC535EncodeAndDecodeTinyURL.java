package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC535EncodeAndDecodeTinyURL extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC535EncodeAndDecodeTinyURL();
    }

    /**
     * 覺得這題真的蠻鳥 沒有任何標準答案, 我就參考各種答案了
     * 後來理解到做 TinyURL 有些業界做法, 但各有pros cons
     * 最蠢的是直接回url, 不過任何 encode/decode 可以過且速度超快
     * 參考一下人家的正常討論, url encode/decode 幾種做法
     * https://leetcode.com/problems/encode-and-decode-tinyurl/discuss/100270/Three-different-approaches-in-java
     * 比較直觀做法是
     * 1. increase counter
     * ->  這篇 https://leetcode.com/problems/encode-and-decode-tinyurl/discuss/100276/Easy-solution-in-java-5-line-code.)
     * ->  也有提到是常見做法用database 來做, a increase number primary key for each unique URL
     * 2. String.hashCode -> collision exist among several strings
     * 3. random function -> collision?
     * 討論也有提到, 要處理concurrency issue, 可能會用到 synchronized blocks, or a ConcurrentMap
     * 不知道屆時真的遇到此問題是否也會被問到
     * 我基本上先用 increasing primary key來做吧, 只要確保 monotonic increasing就好, 而且只要一套 consensus system 就可以做到
     */
    // Encodes a URL to a shortened URL.
    List<String> u = new ArrayList<>();
    public String encode(String longUrl) {
        u.add(longUrl);
        return String.valueOf(u.size() - 1);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int i = Integer.parseInt(shortUrl);
        return i < u.size() ? u.get(i) : "";
    }
}
