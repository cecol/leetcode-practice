package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC1348TweetCountsPerFrequency extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1348TweetCountsPerFrequency();
    }

    /**
     * 我有想到說一定要用 Map 紀錄 tweetName, 但Map value要用什麼紀錄時間沒想法
     * https://leetcode.com/problems/tweet-counts-per-frequency/discuss/503453/Java-TreeMap-Accepted-Solution-Easy-Understand
     * 1. 沒想到 TreeMap Key是 time, Value是 count, 畢竟同一時間可能多次
     * 2. TreeMap.subMap(startTime, endTime + 1) 直接取出要的區間所有可能
     * 3. 要算區間要用bucket來推算 bucket = size = ((endTime - startTime) / interval) + 1;
     * tweet time index in bucket = (tweet time - startTime) / interval;
     *
     */
    public void TweetCounts() {

    }

    Map<String, TreeMap<Integer, Integer>> m = new HashMap<>();

    public void recordTweet(String tweetName, int time) {
        if (!m.containsKey(tweetName)) m.put(tweetName, new TreeMap<>());
        TreeMap<Integer, Integer> tm = m.get(tweetName);
        tm.put(time, tm.getOrDefault(time, 0) + 1);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> res = new ArrayList<>();
        if (!m.containsKey(tweetName)) return res;
        int interval = 0, size = 0;
        if (freq.equals("minute")) interval = 60;
        else if (freq.equals("hour")) interval = 3600;
        else interval = 86400;
        size = ((endTime - startTime) / interval) + 1;
        int[] bucket = new int[size];
        TreeMap<Integer, Integer> tm = m.get(tweetName);
        for (Map.Entry<Integer, Integer> en : tm.subMap(startTime, endTime + 1).entrySet()) {
            int index = (en.getKey() - startTime) / interval;
            bucket[index] += en.getValue();
        }
        for (int n : bucket) res.add(n);
        return res;
    }
}
