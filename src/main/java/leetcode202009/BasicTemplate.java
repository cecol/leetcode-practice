package leetcode202009;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasicTemplate {
  protected Logger log = LoggerFactory.getLogger(this.getClass());

  protected void logIntArray(int[][] d) {
    for (int[] i : d) log.debug("{}", i);
  }
}
