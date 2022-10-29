package leetcode202210.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC278FirstBadVersion extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC278FirstBadVersion();
    }

    public static boolean isBadVersion(int version) {
        return false;
    }

    public int firstBadVersion(int n) {
        int l=1,r=n;
        while(l<r) {
            int m = l + (r-l)/2;
            if(isBadVersion(m)) r=m;
            else l=m+1;
        }
        return l;
    }
}
