package leetcode202105.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC1196HowManyApplesCanYouPutIntoTheBasket extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1196HowManyApplesCanYouPutIntoTheBasket();
    }

    public int maxNumberOfApples(int[] arr) {
        Arrays.sort(arr);
        int c=0,n=arr.length,w=0;
        for(int i=0;i<n;i++) {
            if(w+arr[i]<=5000) {
                w+=arr[i];
                c++;
            } else break;
        }
        return c;
    }
}
