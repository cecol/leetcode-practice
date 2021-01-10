package leetcode20200921to20201031;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasicTemplate {
  protected Logger log = LoggerFactory.getLogger(this.getClass());

  protected void logIntArray(int[][] d) {
    for (int[] i : d) log.debug("{}", i);
  }

  protected void logLongArray(long[][] d) {
    for (long[] i : d) log.debug("{}", i);
  }
  protected void logDoubleArray(double[][] d) {
    for (double[] i : d) log.debug("{}", i);
  }
  protected void logBooleanArray(boolean[][] d) {
    for (boolean[] i : d) log.debug("{}", i);
  }
}
