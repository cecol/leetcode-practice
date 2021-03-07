package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC893GroupsOfSpecial_EquivalentStrings extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC893GroupsOfSpecial_EquivalentStrings();
    }

    /**
     * 這題我有想到就是找到 每個字串奇數,偶數的個別字元組合
     * 所以要用 Set<Character>, 但是單單用Set是不太夠的, 因為如果字串長度不樣或者相同字元出現多次 也是會有Set<Character>沒辦法分辨的情境
     * 多半還是要用到排序, 我就是沒想到排序這一段要用 int[26] 來搞
     * 所以找出每個字串奇偶的int[26] 然後轉成字串放進global Set,最後看看 global set 長度就是答案了
     */
    public int numSpecialEquivGroups(String[] A) {
        Set<String> ss = new HashSet<>();
        for (String a : A) {
            int[] odd = new int[26];
            int[] even = new int[26];
            for (int i = 0; i < a.length(); i++) {
                if (i % 2 == 0) even[a.charAt(i) - 'a']++;
                else odd[a.charAt(i) - 'a']++;
            }
            ss.add(Arrays.toString(even) + Arrays.toString(odd));
        }
        return ss.size();
    }
}
