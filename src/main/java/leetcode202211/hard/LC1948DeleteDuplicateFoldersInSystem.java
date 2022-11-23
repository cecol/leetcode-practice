package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC1948DeleteDuplicateFoldersInSystem extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1948DeleteDuplicateFoldersInSystem();
    }

    /**
     * https://leetcode.com/problems/delete-duplicate-folders-in-system/discuss/2292287/Java-Trie-Node-%2B-Serialization-With-Explanation
     * 沒想到這題要用 Trie 來幫忙建立 directories
     * Trie 記載
     * 1. TreeMap<String, TrieNode> next; sub folders, 必用 TreeMap, 之後 folder serialize 才是唯一
     * 2. List<Integer> idx; 代表目前 Trie folder 是來自原始 List<List<String>> paths 哪裏, 後續可以回頭找回
     * 3. String contents; 當前 folder serialize 結果
     *
     * 要比較 sub folder 是否相同得做出 String serialize()
     * 會長這樣: child1.name:serialize(child1),child2.name:serialize(child2),...childn.name:serialize(childn)
     *
     * findDuplicates:
     * 建樹完就是開始 dfs, 也是 postorder 跟 LC652FindDuplicateSubtrees 很類似
     * 一路 postorder 到 leave 然後找 serialize 相同的出來, 把 idx 都找出來
     * */
    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        TrieNode rt = buildTrie(paths);
        Set<Integer> duplicates = new HashSet<>();
        findDuplicates(rt, new HashMap<>(), duplicates);
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < paths.size(); i++) {
            if (!duplicates.contains(i)) res.add(paths.get(i));
        }
        return res;
    }

    String findDuplicates(TrieNode node, Map<String, List<Integer>> dirs, Set<Integer> dups) {
        for (TrieNode ch : node.ch.values()) {
            String key = findDuplicates(ch, dirs, dups);
            if (key.isEmpty()) continue;

            if (dirs.containsKey(key)) {
                dups.addAll(ch.idx);
                dups.addAll(dirs.get(key));
            } else dirs.put(key, ch.idx);
        }
        return node.serialize();
    }

    TrieNode buildTrie(List<List<String>> paths) {
        TrieNode rt = new TrieNode();
        for (int i = 0; i < paths.size(); i++) {
            TrieNode n = rt;
            for (String dir : paths.get(i)) {
                n = n.addCh(dir);
                n.addIndex(i);
            }
        }
        return rt;
    }

    class TrieNode {
        TreeMap<String, TrieNode> ch = new TreeMap<>();
        List<Integer> idx = new ArrayList<>();
        String content;

        TrieNode() {
        }

        TrieNode addCh(String dir) {
            if (!ch.containsKey(dir)) ch.put(dir, new TrieNode());
            return ch.get(dir);
        }

        void addIndex(int idx) {
            this.idx.add(idx);
        }

        String serialize() {
            if (content != null) return content;
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, TrieNode> en : ch.entrySet()) {
                sb.append(en.getKey());
                sb.append(":");
                sb.append(en.getValue().serialize());
                sb.append(",");
            }

            return content = sb.toString();
        }
    }
}
