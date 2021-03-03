package leetcode20200921to20201031.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC658FindKClosestElements extends BasicTemplate {

    public static void main(String[] args) {
        var LC = new LC658FindKClosestElements();
        var r = LC.findClosestElements(new int[]{0, 0, 1, 2, 3, 3, 4, 7, 7, 8}, 3, 5);
    }

    /**
     * 沒想到2020/10就解過, 2021/3來看當作新題目依樣
     * 然後回來看當初寫的也不太認得
     * 當初完全沒想到要用 binary search
     * 算是用暴力法解掉 竟然也是解的掉
     * 後來參考別人做法覺得應該要精簡一點的想法
     * https://leetcode.com/problems/find-k-closest-elements/discuss/202785/Very-simple-Java-O(n)-solution-using-two-pointers
     * 沒想到可以這麼簡單無腦的用 h-i>=k 過程中不斷縮減到需要的區間, 根據l,h誰離x遠 誰就自己縮短
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int l = 0, h = arr.length - 1;
        while (h - l >= k) {
            if (Math.abs(x - arr[l]) > Math.abs(x - arr[h])) l++;
            else h--;
        }
        List<Integer> res = new ArrayList<>(k);
        while (l <= h) res.add(arr[l++]);
        return res;
    }
}
