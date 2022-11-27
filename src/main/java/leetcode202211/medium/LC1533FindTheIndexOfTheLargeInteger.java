package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC1533FindTheIndexOfTheLargeInteger extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    interface ArrayReader {
        // Compares the sum of arr[l..r] with the sum of arr[x..y]
        // return 1 if sum(arr[l..r]) > sum(arr[x..y])
        // return 0 if sum(arr[l..r]) == sum(arr[x..y])
        // return -1 if sum(arr[l..r]) < sum(arr[x..y])
        public int compareSub(int l, int r, int x, int y);

        // Returns the length of the array
        public int length();
    }

    /**
     * 滿直觀的解法, 關鍵在判斷 l -> r 區間是 odd or even 才來決定要怎麼切左半右半
     * 如果是 odd, 就不包含 mid 下去 compareSub(l, mid - 1, mid + 1, r)
     * 如果是 even 就是 compareSub(l, mid, mid + 1, r)
     * 哪邊大就往哪邊靠
     */
    public int getIndex(ArrayReader reader) {
        int l = 0, r = reader.length() - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int c;
            if (r - mid == mid - l) {
                c = reader.compareSub(l, mid - 1, mid + 1, r);
            } else {
                c = reader.compareSub(l, mid, mid + 1, r);
            }
            if (c >= 0) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}
