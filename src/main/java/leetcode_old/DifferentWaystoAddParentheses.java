package leetcode_old;

import java.util.LinkedList;
import java.util.List;

public class DifferentWaystoAddParentheses {
    public static void main(String[] args) {
        String n1 = "2*3-4*5";
        for (Integer i : diffWaysToCompute(n1)) System.out.print(i + " ");
    }

    public static List<Integer> diffWaysToCompute(String input) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
                String left = input.substring(0, i);
                String right = input.substring(i + 1);
                List<Integer> leftList = diffWaysToCompute(left);
                List<Integer> rightList = diffWaysToCompute(right);
                for (int l : leftList) {
                    for (int r : rightList) {
                        switch (input.charAt(i)) {
                            case '+':
                                list.add(l + r);
                                break;
                            case '-':
                                list.add(l - r);
                                break;
                            case '*':
                                list.add(l * r);
                                break;
                        }
                    }
                }
            }
        }
        if (list.size() == 0) list.add(Integer.valueOf(input));
        return list;
    }
}
