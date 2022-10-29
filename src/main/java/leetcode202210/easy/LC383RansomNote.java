package leetcode202210.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC383RansomNote extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC383RansomNote();
    }

    public boolean canConstruct(String r, String m) {
        int[] mc = new int[26];
        for(char c:m.toCharArray()){
            mc[c-'a']++;
        }
        for(char c:r.toCharArray()){
            mc[c-'a']--;
            if(mc[c-'a'] < 0) return false;
        }
        return true;
    }
}
