package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC1442CountTripletsThatCanFormTwoArraysOfEqualXOR extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC1442CountTripletsThatCanFormTwoArraysOfEqualXOR();
    var s = LC.countTriplets(new int[]{2, 3, 1, 6, 7});
  }

  public int countTriplets(int[] arr) {
    int c = 0;
    if (arr == null || arr.length < 2) return c;
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {

      }
    }
    return c;
  }
}
