package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC983MinimumCostForTickets extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC983MinimumCostForTickets();
    var s = LC.mincostTickets(new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15});
  }

  public int mincostTickets(int[] days, int[] costs) {
    int[] dayCostDp = new int[days[days.length - 1] + 1]; // 0 -> last day
    int dayDpIndex = 1; // start from first day,
    for (int requestDay : days) {
      while (dayDpIndex < requestDay) { // skip non-requestDay
        dayCostDp[dayDpIndex] = dayCostDp[dayDpIndex - 1]; // non-requestDay cost = non-requestDay - 1
        dayDpIndex++;
      }
      int m = dayCostDp[dayDpIndex - 1] + costs[0]; // cost 1 day pass ticket
      int m7pre = dayDpIndex - 7 >= 0 ? dayCostDp[dayDpIndex - 7] : 0; // cost 7 day pass ticket
      m = Math.min(m, m7pre + costs[1]);
      int m30pre = dayDpIndex - 30 >= 0 ? dayCostDp[dayDpIndex - 30] : 0; // cost 30 day pass ticket
      m = Math.min(m, m30pre + costs[2]);
      dayCostDp[dayDpIndex] = m;
      dayDpIndex++; // next day
    }
    log.info("dp: {}", dayCostDp);
    return dayCostDp[dayCostDp.length - 1];
  }
}
