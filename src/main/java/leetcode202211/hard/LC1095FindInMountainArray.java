package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC1095FindInMountainArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1095FindInMountainArray();
    }

    interface MountainArray {
        public int get(int index);

        public int length();
    }

    /**
     * https://leetcode.com/problems/find-in-mountain-array/discuss/1796458/Java-Faster-Then-100
     * 核心概念, 跑三次 binary search
     * 1. 第一次找出 peak, 所以是 mid 跟 mid + 1 比較, if(mid > mid+1) r = mid
     * 2. 第二次從 0 -> peak 找看看 target
     * 3. 第三次 如果第二次沒找到, 就是從 p+1 -> n - 1 找看看 target
     * find(MountainArray num, int t, int l, int r)
     * 裏面就是根據 asc, or desc 來判定要怎麼走
     */
    public int findInMountainArray(int target, MountainArray num) {
        int p = peak(num);
        int f = find(num, target, 0, p);
        if (f != -1) return f;
        return find(num, target, p + 1, num.length() - 1);
    }

    int peak(MountainArray num) {
        int l = 0, r = num.length() - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (num.get(m) > num.get(m + 1)) r = m;
            else l = m + 1;
        }
        return l;
    }

    int find(MountainArray num, int t, int l, int r) {
        boolean asc = num.get(l) < num.get(r);
        while (l < r) {
            int m = l + (r - l) / 2;
            if (asc) {
                if (num.get(m) >= t) r = m;
                else l = m + 1;
            } else {
                if (num.get(m) <= t) r = m;
                else l = m + 1;
            }
        }
        return num.get(l) == t ? l : -1;
    }
}
