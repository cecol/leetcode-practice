package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class LC355DesignTwitter extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC355DesignTwitter();
        LC.postTweet(1, 5);
        LC.postTweet(1, 3);
        LC.postTweet(1, 101);
        LC.postTweet(1, 13);
        LC.postTweet(1, 10);
        LC.postTweet(1, 2);
        LC.postTweet(1, 94);
        LC.postTweet(1, 505);
        LC.postTweet(1, 333);
        LC.getNewsFeed(1);
    }

    public void Twitter() {

    }

    Map<Integer, Set<Integer>> followM = new HashMap<>();
    Map<Integer, List<int[]>> posts = new HashMap<>();

    int i = 0;

    /**
     * 花了很時間才做好(卡在以為 tweetId 是有時間序關係, 結果沒有, 得自己建timestamp)
     * 做的版本是沒有做 cache for NewsFeed
     * 我一開始以為沒有建立NewsFeed cache會很慢, 但是結果
     * 22 ms, faster than 89.04% , 44.3 MB, less than 97.19%
     * 試了幾個說比較快的好像也沒這麼快
     * 所以就不特別做cache了, 因為太多複雜case 要處理
     * 所以就單純的
     * Map<Integer, Set<Integer>> followM = new HashMap<>(); -> follow relationship
     * Map<Integer, List<int[]>> posts = new HashMap<>(); -> user's tweets
     * int i = 0; -> timestamp of tweet
     * getNewsFeed -> 把自己還有followee的 tweet 集中放在 max heap -> 取出前10個回傳
     */
    public void postTweet(int userId, int tweetId) {
        if (!posts.containsKey(userId)) posts.put(userId, new ArrayList<>());
        posts.get(userId).add(new int[]{tweetId, i++});
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<int[]> q = new PriorityQueue<int[]>((i1, i2) -> -i1[1] - -i2[1]);
        if (!posts.containsKey(userId)) posts.put(userId, new ArrayList<>());
        q.addAll(posts.get(userId));
        if (followM.containsKey(userId)) {
            for (Integer fee : followM.get(userId)) {
                if (posts.containsKey(fee)) q.addAll(posts.get(fee));
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= 10 && q.size() > 0; i++) res.add(q.poll()[0]);
        return res;
    }

    public void follow(int followerId, int followeeId) {
        if (followeeId == followerId) return;
        if (!followM.containsKey(followerId)) followM.put(followerId, new HashSet<>());
        followM.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followeeId == followerId) return;
        if (!followM.containsKey(followerId)) followM.put(followerId, new HashSet<>());
        followM.get(followerId).remove(followeeId);
    }
}
