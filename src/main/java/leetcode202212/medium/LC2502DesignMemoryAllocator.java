package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Stack;

public class LC2502DesignMemoryAllocator extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC2502DesignMemoryAllocator();
    }

    /**
     * https://leetcode.com/problems/design-memory-allocator/solutions/2899364/java-array-solution/
     * 當下時間不夠解出來, 但看到答案後才發現太蠢
     * 以為要什麼優化找 allocate & free
     * <p>
     * 直接一個 array[n] 下去暴力解就好....
     *
     * allocate 就是  int startIdx = 0, endIdx = 0;
     * 從頭開始找一個區間 count == size, 沒有 return -1
     * 有就可以回傳
     */
    class Allocator {

        int[] array;

        public Allocator(int n) {
            array = new int[n];
        }

        public int allocate(int size, int mID) {
            if (size > array.length) return -1;
            int startIdx = 0, endIdx = 0;
            int count = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] == -1) {
                    count++;
                } else {
                    count = 0;
                    startIdx = i + 1;
                }
                if (count == size) endIdx = i;
            }
            if (startIdx > endIdx) return -1;
            Arrays.fill(array, startIdx, Math.min(endIdx + 1, array.length), mID);
            return startIdx;
        }

        public int free(int mID) {
            int c = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] == mID) {
                    c++;
                    array[i] = -1;
                }
            }
            return c;
        }
    }
}
