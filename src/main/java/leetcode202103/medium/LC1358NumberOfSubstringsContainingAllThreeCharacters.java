package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC1358NumberOfSubstringsContainingAllThreeCharacters extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1358NumberOfSubstringsContainingAllThreeCharacters();
        LC.numberOfSubstrings("abcabc");
    }

    public int numberOfSubstrings(String s) {
        int i = 0, res = 0, n = s.length();
        int[] count = new int[3];
        for (int j = 0; j < n; j++) {
            count[s.charAt(j) - 'a']++;
            while (fullfill(count)) {
                res += n - j;
                count[s.charAt(i) - 'a']--;
                i++;
            }
        }
        return res;
    }

    private boolean fullfill(int[] c) {
        boolean r = true;
        for (int i : c) r &= i > 0;
        return r;
    }
}
