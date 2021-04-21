package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.PriorityQueue;

public class LC420StrongPasswordChecker extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC420StrongPasswordChecker();
    }

    /**
     * https://leetcode.com/problems/strong-password-checker/discuss/151333/Easy-O(n)-solution
     * 這題要考的應該就是各種 constrains 的 generalization
     * If the length of string is 3 or less, only insertion is needed;
     * If the length is 4, two insertions and might one replacement are needed;
     * if the length is 5, one insertion and might two replacements are needed.
     * So it's easy to solve for the case that length is less than 6: Math.Max(requiredChar, 6 - s.Length);
     * The requiredChar is at most 3.
     * 上面把字串長度小於6簡化成 Math.Max(requiredChar, 6 - s.Length); -> 想辦法補成6且合乎規則就好
     * ex: length = 3 -> 只要insert其他缺的就好(缺數字缺大小寫就insert對應的, 就算給的是三個重複 aaa, 穿插insert也就解了 aaa 問題)
     * ex: length = 4 -> insert不足的2 解大小寫與數字限制 -> 用 replacement
     * ex: length = 5 -> insert不足的1 解大小寫與數字限制 -> 用 replacement
     * <p>
     * For 6<=length<=20, only replacements are needed.
     * 在 6與20間就是處理 replacement 處理repeated chars (當然如果reqChars 更大, 就是replacement 順便換缺乏的 required)
     * int replace = 0; // total replacements for repeated chars. e.g. "aaa" needs 1 replacement to fix
     * <p>
     * If > 20, only replacements and deletions are needed.
     * For the case of a repeated substring which length is multiple of 3 (note 3n, e.g. "aaa"), then one deletion will save a replacement to fix it;
     * For the case of a repeated substring which length is multiple of 3 plus 1 (note 3n+1, e.g. "aaaa"), then two deletions will save a replacement to fix it;
     * For the case of a repeated substring which length is multiple of 3 plus 2 (note 3n+2, e.g. "aaaaa"), then three deletions will save a replacement to fix it;
     * <p>
     * 大於20字元 先算 delete 總數
     * 如何用 deleteCount 來取代部分 replacements ?
     * ex: for repeated aaaaaaaaa -> 9a
     * 算出來的 replace = 3, onced = 1, twod = 0
     * 其中可分解為 3組 repeated: 1:aaa 2:aaa 3:aaa
     * replace -= Math.Min(deleteCount, oned); -> 先減去第三組 3:aaa -> 只要刪掉一個變成aa就合法, 所以replace:delete比值 1:1
     * replace -= Math.Min(Math.Max(deleteCount - oned, 0), twod) / 2; 因為該組的 %3 != 1, twod = 0, 所以這邊沒減去
     * replace -= Math.Max(deleteCount - oned - twod, 0) / 3; -> 拿剩下的deleteCount再去減去 1:aaa 2:aaa
     * 每組都是三個字元都刪掉, 刪掉三個字元, 所以replace:delete比值 1:3 -> 3個delete省一個replace, why? 因為deleteCount是一定要執行, 但replace是次之
     * 所以拿deleteCount換replace都是划算
     * <p>
     * ex: for repeated aaaaaaaa -> 7a
     * 算出來的 replace = 2, onced = 0, twod = 2
     * 其中可分解為 2組 repeated: 1:aaa 2:aaaa
     * replace -= Math.Min(deleteCount, oned); -> 因為該組的 %3 != 0, onced = 0, 所以這邊沒減去
     * replace -= Math.Min(Math.Max(deleteCount - oned, 0), twod) / 2; 因為該組的 %3 == 1, twod = 2, 先減去第2組 2:aaaa -> 只要刪掉2個變成aa就合法, 所以replace:delete比值 1:2
     * replace -= Math.Max(deleteCount - oned - twod, 0) / 3; -> 拿剩下的deleteCount再去減去 1:aaaa ((同上組
     * <p>
     * int oned = 0; // total deletions for 3n repeated chars. e.g. "aaa" needs 1 deletion to fix
     * int twod = 0; // total deletions for 3n+1 repeated chars. e.g. "aaaa" needs 2 deletions to fix.
     * 1. deleting 1 char in (3n) repeated chars will save one replacement
     * -> replace -= Math.Min(deleteCount, oned);
     * <p>
     * 2. deleting 2 chars in (3n+1) repeated chars will save one replacement
     * -> replace -= Math.Min(Math.Max(deleteCount - oned, 0), twod) / 2;
     * <p>
     * 3. deleting 3 chars in (3n+2) repeated chars will save one replacement
     * -> replace -= Math.Max(deleteCount - oned - twod, 0) / 3;
     * <p>
     * 所以重點是
     * 1. 算出 required chars -> 大寫1 小寫1 數字1
     * 2. len < 6 -> 取 max of (required chars, 6 - len)
     * 3. 6 <= len <= 20 -> 取 replace for repeated case, 取 max of (required chars, repeated)
     * 4. len > 20 -> 取 replace + delete(replace 可被 delete 消) 取 deleteCount + max of (required chars, repeated)
     */
    public int strongPasswordChecker(String password) {
        int n = password.length();
        int reqChars = getRequiredCount(password);
        if (n < 6) return Math.max(reqChars, 6 - n);

        int replace = 0;
        int oned = 0;
        int twod = 0;
        char[] s = password.toCharArray();
        for (int i = 0; i < n; ) {
            int len = 1;
            while (i + len < n && s[i + len] == s[i + len - 1]) len++;
            if (len >= 3) {
                replace += len / 3;
                if (len % 3 == 0) oned += 1;
                if (len % 3 == 1) twod += 2;
            }
            i += len;
        }
        if (n <= 20) return Math.max(reqChars, replace);
        int delete = n - 20;
        replace -= Math.min(delete, oned);
        replace -= Math.min(Math.max(delete - oned, 0), twod) / 2;
        replace -= Math.max(delete - oned - twod, 0) / 3;
        return delete + Math.max(reqChars, replace);
    }

    private int getRequiredCount(String p) {
        int lw = 1, up = 1, di = 1;
        for (char c : p.toCharArray()) {
            if (Character.isLowerCase(c)) lw = 0;
            else if (Character.isDigit(c)) di = 0;
            else if (Character.isUpperCase(c)) up = 0;
        }
        return lw + up + di;
    }
}
