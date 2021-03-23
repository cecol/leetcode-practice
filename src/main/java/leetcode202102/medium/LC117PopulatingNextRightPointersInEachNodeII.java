package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Node;
import util.TreeNode;

public class LC117PopulatingNextRightPointersInEachNodeII extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC117PopulatingNextRightPointersInEachNodeII();
    var s = LC.connect(null);
  }

  /**
   * 原本想用116解法改變一下來解  但看起來完全行不通
   * 也不能真的每一層level order去走
   * 參考別人的想法:
   * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/discuss/37813/Java-solution-with-constant-space
   * <p>
   * 花了好久才參透 dummyNode -> 因為註解有說 -> this head will always point to the first element in the current layer we are searching
   * 我一直以為 dummyNode 是每一層的第一個, 所以應該會在過程中被換
   * 但我一直沒找到 dummyNode = xxx_next_level_first 的概念
   * 後來才參透, 基本上就一層一層的下去, 要過程中記住 current level 的 current node 以及 parent level的 current node
   * 利用 parent level的 current node 來幫助 current level 的 current node 指到下一個 next node
   * if (curParent.left != null) && if (curParent.right != null) -> 這邊有兩層含義
   * 1. curParent是新的一層 -> 這時候 curLevelNode其實是指向一個假的 dummyNode ->
   * -> 所以 curLevelNode.next = curParent.left; 會造成實際上是 dummyNode.next = curParent.left;
   * -> 但這沒關係, 最後會把 dummyNode.next = null
   * -> 下一步就是 curLevelNode = curLevelNode.next; -> curLevelNode 往下一個走, 就是走到剛剛指到的 curParent.left
   * -> 此時 curLevelNode 就是名符其實的當前level第一個
   * 2. curParent還是同一層只是已經往下一個next走了 -> 所以就next繼續往 parent有的 child走下去 -> 然後在走向next
   * 當 curParent 左右子樹都看完了 -> curParent level 往下一個走
   * 1. 還有 parent level -> current level 繼續走, 回到 while (curParent != null) 一開始繼續用 parent 左右子樹幫curLevelNode找next
   * 2. if(curParent == null) 代表 curParent 已經走到盡頭 -> 要進行換 level
   * 基本概念是 -> curParent 要往下層走 , curLevelNode 也要往下層走, 但實際 code 作法比較特別
   * curLevelNode = dummyNode -> curLevelNode 先回到 dummyNode
   * 要記得 dummyNode 一直都是 Node dummyNode = new Node(0); -> 永遠都沒變過
   * 接下來 curParent = dummyNode.next; -> 這邊就是重點!!! 此時的 dummyNode.next; 是因為前面提到
   * ’所以 curLevelNode.next = curParent.left; 會造成實際上是 dummyNode.next = curParent.left;‘
   * 所以 dummyNode.next 一直都保持著指向 current level 的 first node
   * 所以 curParent = dummyNode.next; 就是走向 next parent level 的 first node
   * dummyNode.next = null; -> 你可能會以為這樣 dummyNode.next 又不見了 下一層怎又接回來?
   * 又正好因為
   * curLevelNode = dummyNode -> curLevelNode 先回到 dummyNode 配上回到while (curParent != null) ->
   * ’所以 curLevelNode.next = curParent.left; 會造成實際上是 dummyNode.next = curParent.left;‘
   * 因此dummyNode.next 其實又跑向 下下層 level 的 current node
   * 所以一直在操弄的是
   * dummyNode 幫助切換 current node
   * dummyNode.next 來幫助切換 current parent ->
   * 利用一開始 curLevelNode = dummyNode => curLevelNode.next = curParent.left; == dummyNode.next = curParent.left
   *
   * 2刷 重點整理
   * 1. dummy 永遠指向 Node(0),
   * 2. curParent.next 永遠都已經接好, curParent.next都接好了, 所以可依一直 curParent = curParent.next
   * 3. 真正在level走的是 curLevelNode, 他會一直
   * -> 1. 有得走: curLevelNode = curParent.left or curParent.right  然後 curLevelNode = curLevelNode.next走下去
   * -> 2. 沒得走(curParent.next == null), 得換層, curLevelNode先走回dummy, 固定位置,
   * ->     dummy.next 仍是 curLevel 第一個所以 curParent 要往下走的話, 就得 curParent = dummy.next達成換層
   * ->     從頭到尾都是 dummy.next來當換層
   * 4. 用迴圈解就好, 我通常都以為 tree用遞迴解, 然後就在想先遞迴左子樹還是右子樹, 但事實上如果是 level 走下去, 其實應該 while可以
   *
   * 3刷
   * 總算釐清為什麼 走完當層之後得dummyNode.next = null;
   * 這是為了最後一層, 當curParent來到最後一層, 就會都沒有 left & right 要 next
   * 就又會走回  curParent = dummyNode.next; 然後進入無窮迴圈, 一直在最後一層走下去
   */
  public Node connect(Node root) {
    Node dummyNode = new Node(0);
    Node curLevelNode = dummyNode;
    Node curParent = root;
    while (curParent != null) {
      if (curParent.left != null) {
        curLevelNode.next = curParent.left;
        curLevelNode = curLevelNode.next;
      }
      if (curParent.right != null) {
        curLevelNode.next = curParent.right;
        curLevelNode = curLevelNode.next;
      }
      curParent = curParent.next;
      if (curParent == null) {
        curLevelNode = dummyNode;
        curParent = dummyNode.next;
        dummyNode.next = null;
      }
    }
    return root;
  }

    /**
     * 二刷後覺得 dummy的做法是比較精簡但不直覺, 因為是透過dummy.next來換層,
     * 其實重點是要
     * 1. 換層邏輯 -> 記住當前層的 head 來讓當parent走完時候換層
     * 2. 還沒換層一直往下走 -> 當前層的cur.next 一直往下指
     * 這個解法code比較多一點, 但速度應該差不多
     * */
    public Node connect2(Node root) {
        Node cur = null;
        Node curH = null;
        Node p = root;
        while(p != null) {
            if(p.left != null) {
                if(cur == null) {
                    cur = p.left;
                    curH=cur;
                } else {
                    cur.next = p.left;
                    cur=cur.next;
                }
            }
            if(p.right != null) {
                if(cur == null) {
                    cur = p.right;
                    curH=cur;
                } else {
                    cur.next = p.right;
                    cur=cur.next;
                }
            }
            p=p.next;
            if(p==null) {
                p=curH;
                cur=null;
                curH=null;
            }
        }
        return root;
    }
}
