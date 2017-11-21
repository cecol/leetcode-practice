import java.util.*;

public class LadderLength {
    public static void main(String[] a) {
        String[] s1 = {"hit", "cog"};
        List<String> w1 = new LinkedList<String>();
        w1.add("hot");
        w1.add("dot");
        w1.add("dog");
        w1.add("lot");
        w1.add("log");
        w1.add("cog");
        System.out.println(ladderLength(s1[0], s1[1], w1));

        String[] s2 = {"hot", "dog"};
        List<String> w2 = new LinkedList<String>();
        w2.add("hot");
        w2.add("dog");
        //w1.add("cog");
        System.out.println(ladderLength(s2[0], s2[1], w2));
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> bfs = new LinkedList<>();
        HashSet<String> viewed = new HashSet<String>();
        HashSet<String> wd = new HashSet<String>(wordList);

        bfs.add(beginWord);
        bfs.add(null);
        viewed.add(beginWord);

        int count = 1;
        while (!bfs.isEmpty()) {
            String s = bfs.poll();
            if (s != null) {
                for (int i = 0; i < s.length(); i++) {
                    char[] tc = s.toCharArray();
                    for (char a = 'a'; a <= 'z'; a++) {
                        tc[i] = a;
                        String temp = new String(tc);
                        if (wd.contains(temp) && !viewed.contains(temp)) {
                            if (temp.equals(endWord)) return count + 1;
                            bfs.add(temp);
                            viewed.add(temp);
                        }
                    }
                }
            } else {
                count++;
                if(!bfs.isEmpty()) bfs.add(null);
            }
        }
        return 0;
    }
}
