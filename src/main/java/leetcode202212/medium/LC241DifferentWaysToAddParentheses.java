package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC241DifferentWaysToAddParentheses extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC241DifferentWaysToAddParentheses();
    }

    public List<Integer> diffWaysToCompute(String exp) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < exp.length(); i++) {
            if (!Character.isDigit(exp.charAt(i))) {
                List<Integer> left = diffWaysToCompute(exp.substring(0, i));
                List<Integer> right = diffWaysToCompute(exp.substring(i + 1));
                for (int l = 0; l < left.size(); l++)
                    for (int r = 0; r < right.size(); r++) {
                        if(exp.charAt(i) == '-') res.add(left.get(l) - right.get(r));
                        else if(exp.charAt(i) == '+') res.add(left.get(l) + right.get(r));
                        else if(exp.charAt(i) == '*') res.add(left.get(l) * right.get(r));
                        else res.add(left.get(l) / right.get(r));
                    }
            }
        }
        if(res.size() == 0) res.add(Integer.parseInt(exp));
        return res;
    }
}
