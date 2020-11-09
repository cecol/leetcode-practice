package leetcode202011.easy;

import leetcode20200921to20201031.BasicTemplate;
import leetcode20200921to20201031.easy.LC1528ShuffleString;

public class LC1342NumberOfStepsToReduceANumberToZero extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC1342NumberOfStepsToReduceANumberToZero();
    var s = LC.numberOfSteps(123);
  }


  public int numberOfSteps (int num) {
    int c = 0;
    while (num > 0) {
      if( (num & 1) == 1) {
        num-=1;
      } else {
        num/=2;
      }
      c++;
    }
    return c;
  }
}
