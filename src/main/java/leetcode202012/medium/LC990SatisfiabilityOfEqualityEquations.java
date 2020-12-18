package leetcode202012.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC990SatisfiabilityOfEqualityEquations extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC990SatisfiabilityOfEqualityEquations();
    var s = LC.equationsPossible(new String[]{"a==b", "b!=a"});
    var s1 = LC.equationsPossible(new String[]{"b==a", "a==b"});
    var s2 = LC.equationsPossible(new String[]{"a==b", "b==c", "a==c"});
    var s3 = LC.equationsPossible(new String[]{"a==b", "b!=c", "c==a"});
    var s4 = LC.equationsPossible(new String[]{"c==c", "b==d", "x!=z"});
    var s5 = LC.equationsPossible(new String[]{"b!=f", "c!=e", "f==f", "d==f", "b==f", "a==f"});
  }

  public boolean equationsPossible(String[] equations) {
    int n = 0;
    Map<Character, Integer> m = new HashMap<>();
    for (String s : equations) {
      if (!m.containsKey(s.charAt(0))) {
        m.put(s.charAt(0), n);
        n++;
      }
      if (!m.containsKey(s.charAt(3))) {
        m.put(s.charAt(3), n);
        n++;
      }
    }
    int[] p = new int[n];
    for (int i = 0; i < n; i++) p[i] = i;
    for (String s : equations) {
      if (s.charAt(1) == '=') {
        if (s.charAt(0) != s.charAt(3)) {
          union(p, m.get(s.charAt(0)), m.get(s.charAt(3)));
        }
      }
    }
    boolean res = true;
    for (String s : equations) {
      if (s.charAt(1) == '!') {
        int i = m.get(s.charAt(0));
        int j = m.get(s.charAt(3));
        if (find(p, i) == find(p, j)) res = false;
      }
    }
    return res;
  }

  public int find(int[] p, int i) {
    while (i != p[i]) {
      i = p[i];
    }
    return i;
  }

  public void union(int[] p, int i, int j) {
    int ir = find(p, i);
    int jr = find(p, j);
    if (ir == jr) return;
    p[jr] = ir;
  }
}
