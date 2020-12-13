package leetcode202012.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;

public class LC131PalindromePartitioning extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC131PalindromePartitioning();
    var s = LC.partition("aab");
  }

  public List<List<String>> partition(String s) {
    List<List<String>> res = new ArrayList<>();
    backtrack(res, new ArrayList<>(), s, 0);
    return res;
  }

  public void backtrack(List<List<String>> res, List<String> t, String s, int b) {
    if (b == s.length()) res.add(new ArrayList<>(t));
    else {
      for (int i = b; i < s.length(); i++) {
        if (isP(s, b, i)) {
          t.add(s.substring(b, i + 1));
          backtrack(res, t, s, i + 1);
          t.remove(t.size() - 1);
        }
      }
    }
  }

  public boolean isP(String s, int b, int e) {
    while (b < e) if (s.charAt(b++) != s.charAt(e--)) return false;
    return true;
  }
}
