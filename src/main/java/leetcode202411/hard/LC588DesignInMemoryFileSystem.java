package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

import java.util.*;

public class LC588DesignInMemoryFileSystem extends BasicTemplate {
    public static void main(String[] args) {
    }

    public void FileSystem() {

    }

    // 沒過
    // 1. File class, boolean isF, fs -> Map
    // 2. path.Split("/"), for loop 過程中 directory == "" 要跳掉
    class File {
        boolean isF;
        String c = "";
        Map<String, File> fs = new HashMap<>();
    }

    File root = new File();

    public List<String> ls(String path) {
        String[] dirs = path.split("/");
        File cur = root;
        String name = "";
        List<String> res = new ArrayList<>();
        for (String d : dirs) {
            if (d.length() == 0) continue;
            if (!cur.fs.containsKey(d)) return res;
            cur = cur.fs.get(d);
            name = d;
        }
        if (cur.isF) res.add(name);
        else for (String k : cur.fs.keySet()) res.add(k);
        Collections.sort(res);
        return res;
    }

    public void mkdir(String path) {
        String[] dirs = path.split("/");
        File cur = root;
        for (String d : dirs) {
            if (d.length() == 0) continue;
            if (!cur.fs.containsKey(d)) cur.fs.put(d, new File());
            cur = cur.fs.get(d);
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] dirs = filePath.split("/");
        File cur = root;
        for (String d : dirs) {
            if (d.length() == 0) continue;
            if (!cur.fs.containsKey(d)) cur.fs.put(d, new File());
            cur = cur.fs.get(d);
        }
        cur.isF = true;
        cur.c += content;
    }

    public String readContentFromFile(String filePath) {
        String[] dirs = filePath.split("/");
        File cur = root;
        for (String d : dirs) {
            if (d.length() == 0) continue;
            if (!cur.fs.containsKey(d)) cur.fs.put(d, new File());
            cur = cur.fs.get(d);
        }
        return cur.c;
    }
}
