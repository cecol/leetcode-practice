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

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> r = new ArrayList<>();
        int i = 0;
        while (arr[i] < x && i < arr.length) i++;
        i--;
        int j = i + 1;
        int[] ra = new int[k];
        int rai = 0;
        while (rai < ra.length) {
            int xi = i >= 0 ? Math.abs(arr[i] - x) : Integer.MAX_VALUE;
            int xj = j < arr.length ? Math.abs(arr[j] - x) : Integer.MAX_VALUE;
            if (xi <= xj) {
                ra[rai] = arr[i];
                i--;
            } else {
                ra[rai] = arr[j];
                j++;
            }
            rai++;
        }
        Arrays.sort(ra);
        for (int a : ra) r.add(a);
        return r;
    }
}
