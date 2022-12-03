package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LC71SimplifyPath extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC71SimplifyPath();
    }

    /**
     * https://leetcode.com/problems/simplify-path/solution/
     * 對 unix path 很不熟, 看到解答才發現很直觀
     * 1. 直接 for go through path.split("/");
     * 2. "." or 空字串 直接省略
     * 2. ".." 也不會加入 stack, 只是如果 stack.size() > 0, 得 stack.pop
     * 3. stack.push(p)
     * 4. 最後準備 StringBuilder, go through sk from bottom -> for (String a : sk)
     * */
    public String simplifyPath(String path) {
        String[] sp = path.split("/");
        Stack<String> sk = new Stack<>();
        for (String a : sp) {
            if (a.equals(".") || a.length() == 0) ;
            else if (a.equals("..")) {
                if (sk.size() > 0) sk.pop();
            } else sk.push(a);
        }
        StringBuilder sb = new StringBuilder();
        for (String a : sk) sb.append("/" + a);
        return sb.length() > 0 ? sb.toString() : "/";
    }
}
