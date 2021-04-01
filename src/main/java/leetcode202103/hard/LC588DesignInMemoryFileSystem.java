package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC588DesignInMemoryFileSystem extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC588DesignInMemoryFileSystem();
    }

    /**
     * https://leetcode.com/problems/design-in-memory-file-system/
     * 沒什麼想法, 直到看到人家的做法就明白
     * 1. 直接建立File class, 用Trie 的概念
     */
    public void FileSystem() {

    }

    class File {
        boolean isF = false;
        Map<String, File> ch = new HashMap<>();
        String c = "";
    }

    File r = new File();

    public List<String> ls(String path) {
        String[] dirs = path.split("/");
        File node = r;
        List<String> res = new ArrayList<>();
        String name = "";
        for (String dir : dirs) {
            if (dir.length() == 0) continue;
            if (!node.ch.containsKey(dir)) return res;
            node = node.ch.get(dir);
            name = dir;
        }
        if (node.isF) res.add(name);
        else for (String k : node.ch.keySet()) res.add(k);
        Collections.sort(res);
        return res;
    }

    public void mkdir(String path) {
        String[] dirs = path.split("/");
        File node = r;
        for (String dir : dirs) {
            if (dir.length() == 0) continue;
            if (!node.ch.containsKey(dir)) {
                File f = new File();
                node.ch.put(dir, f);
            }
            node = node.ch.get(dir);
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] dirs = filePath.split("/");
        File node = r;
        for (String dir : dirs) {
            if (dir.length() == 0) continue;
            if (!node.ch.containsKey(dir)) {
                File f = new File();
                node.ch.put(dir, f);
            }
            node = node.ch.get(dir);
        }
        node.isF = true;
        node.c += content;
    }

    public String readContentFromFile(String filePath) {
        String[] dirs = filePath.split("/");
        File node = r;
        for (String dir : dirs) {
            if (dir.length() == 0) continue;
            if (!node.ch.containsKey(dir)) {
                File f = new File();
                node.ch.put(dir, f);
            }
            node = node.ch.get(dir);
        }
        return node.c;
    }
}
