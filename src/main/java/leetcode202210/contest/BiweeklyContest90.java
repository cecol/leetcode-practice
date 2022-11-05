package leetcode202210.contest;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class BiweeklyContest90 extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new BiweeklyContest90();
    }

    public String oddString(String[] words) {
        Map<String, Integer> m = new HashMap<>();
        Map<String, String> m2 = new HashMap<>();
        for (String w : words) {
            int[] dd = new int[w.length() - 1];
            for (int i = 1; i < w.length(); i++) {
                dd[i - 1] = w.charAt(i) - w.charAt(i - 1);
            }
            String wdd = Arrays.toString(dd);
            m2.put(wdd, w);
            m.put(wdd, m.getOrDefault(wdd, 0) + 1);
        }
        for (Map.Entry<String, Integer> en : m.entrySet()) {
            if (en.getValue() == 1) return m2.get(en.getKey());
        }
        return "";
    }


    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> res = new ArrayList<>();
        for (String q : queries)
            for (String d : dictionary) {
                if (can(q, d)) {
                    res.add(q);
                    break;
                }
            }
        return res;
    }

    public boolean can(String s1, String s2) {
        int c = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) c++;
        }
        return c <= 2;
    }

    // TLE
    public int destroyTargets(int[] nums, int space) {
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> kill = new ArrayList<>();
            kill.add(nums[i]);
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[j] - nums[i]) % space == 0) {
                    kill.add(nums[j]);
                }
            }
            if (kill.size() > res.size()) res = kill;
        }
        return res.get(0);
    }

    public int[] secondGreaterElement(int[] nums) {
        return null;
    }
}
