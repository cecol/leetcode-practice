package leetcode202105.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC1081SmallestSubsequenceOfDistinctCharacters extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1081SmallestSubsequenceOfDistinctCharacters();
    }

    /**
     * 花了很多時間都沒想出來, sub-sequence題型看來還不是很熟悉
     * https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/discuss/889488/Java-Solution-using-Stack
     * 參考了答案才發現幾件事
     * 1. 用Stack 存subsequence, Stack is monotonic increasing subsequence
     * 2. 當前字元已經在stack裡面了就跳過, 直到後面有其它字元進來 -> 且該字元比後面字元大, 且該字元後面還會出現
     * -> 那就移出該字元
     * -> 所以每個字元第一次出現, 都會加入 stack, 但該字元若
     * -> 1. 後面還再出現 與 2. 後面還有比他更小的字元出現 且該更小字元比他的duplicated早出現
     * 那該字元會在遇到比他小的字元時候被刪除
     *
     * 所以關鍵是Stack 的 maintain 是monotonic increasing subsequence
     * 若 curChar < stack.peek() 就代表 stack 得 pop()
     * 但是若是 stack.peek() 後面不再出現了 -> 代表不行 pop()
     * 所以留在 stack 必然是 monotonic increasing or 若沒有呈現 monotonic increasing, 代表該字元已經無法在後面再出現,
     * -> 該字元出現的stack位置已經是他 monotonic increasing subsequence 的最佳可能性
     *
     * 關鍵!!所以需要
     * 1 .char count -> 來確認沒有造成 stack monotonic increasing 的 stack.peek() 是不是後面還有
     * 2. visited[] -> 看看當前字元是否在 stack中, 若在, 就跳過吧, 因為當前的 stack() 已經是最佳的 monotonic increasing subsequence
     * 3. Stack<Character> 來記錄 monotonic increasing subsequence
     * -> 若下一個字元進來 沒有造成 monotonic increasing 且沒造成monotonic increasing的 stack.peek()後面還有出現
     * -> visited[stack.pop()] = false;
     *
     * 所以這題是 Greedy + Stack
     * https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/discuss/308210/JavaC%2B%2BPython-Stack-Solution-O(N)
     * 這邊有更多的 stack 題目
     */
    public String smallestSubsequence(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;
        boolean[] visited = new boolean[26];
        Stack<Character> sk = new Stack<>();
        for (char c : s.toCharArray()) {
            count[c - 'a']--;
            if (visited[c - 'a']) continue;
            while (sk.size() > 0 && sk.peek() > c && count[sk.peek() - 'a'] > 0) visited[sk.pop() - 'a'] = false;
            sk.push(c);
            visited[c - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        for(Character c:sk) sb.append(c);
        return sb.reverse().toString();
    }
}
