package leetcode_old;

import java.util.*;

public class LongestWordInDictionaryThroughDeleting {
    public static void main(String[] a) {
        String s1 = "abpcplea";
        List<String> l = new LinkedList<>();
        l.add("ale");
        l.add("apple");
        l.add("monkey");
        l.add("plea");

        System.out.println(findLongestWord(s1, l));

        String s2 = "wordgoodgoodgoodbestword";
        List<String> l2 = new LinkedList<>();
        l2.add("word");
        l2.add("good");
        l2.add("best");
        l2.add("good");
        System.out.println(findLongestWord(s2, l2));


        String s3 = "mjmqqjrmzkvhxlyruonekhhofpzzslupzojfuoztvzmmqvmlhgqxehojfowtrinbatjujaxekbcydldglkbxsqbbnrkhfdnpfbuaktupfftiljwpgglkjqunvithzlzpgikixqeuimmtbiskemplcvljqgvlzvnqxgedxqnznddkiujwhdefziydtquoudzxstpjjitmiimbjfgfjikkjycwgnpdxpeppsturjwkgnifinccvqzwlbmgpdaodzptyrjjkbqmgdrftfbwgimsmjpknuqtijrsnwvtytqqvookinzmkkkrkgwafohflvuedssukjgipgmypakhlckvizmqvycvbxhlljzejcaijqnfgobuhuiahtmxfzoplmmjfxtggwwxliplntkfuxjcnzcqsaagahbbneugiocexcfpszzomumfqpaiydssmihdoewahoswhlnpctjmkyufsvjlrflfiktndubnymenlmpyrhjxfdcq";
        String s4 = "ettphsiunoglotjlccurlre";
        System.out.println(checkS1ContainS2(s3, s4));

        String s5 = "wsmzffsupzgauxwokahurhhikapmqitytvcgrfpavbxbmmzdhnrazartkzrnsmoovmiofmilihynvqlmwcihkfskwozyjlnpkgdkayioieztjswgwckmuqnhbvsfoevdynyejihombjppgdgjbqtlauoapqldkuhfbynopisrjsdelsfspzcknfwuwdcgnaxpevwodoegzeisyrlrfqqavfziermslnlclbaejzqglzjzmuprpksjpqgnohjjrpdlofruutojzfmianxsbzfeuabhgeflyhjnyugcnhteicsvjajludwizklkkosrpvhhrgkzctzwcghpxnbsmkxfydkvfevyewqnzniofhsriadsoxjmsswgpiabcbpdjjuffnbvoiwotrbvylmnryckpnyemzkiofwdnpnbhkapsktrkkkakxetvdpfkdlwqhfjyhvlxgywavtmezbgpobhikrnebmevthlzgajyrmnbougmrirsxi";
        String s6 = "jpthiudqzzeugzwwsngebdeai";
        System.out.println(checkS1ContainS2(s5, s6));
    }


    public static String findLongestWord(String s, List<String> d) {
        if (s == null || s.length() == 0 || d == null || d.size() == 0) return "";

        HashMap<Integer, List<String>> dictMap = new HashMap<>();
        int max = 0;
        for (String sub : d) {
            if (sub.length() > s.length()) continue;
            if (dictMap.get(sub.length()) == null) {
                List<String> l = new LinkedList<>();
                l.add(sub);
                dictMap.put(sub.length(), l);
            } else {
                putIntoOrderList(sub, dictMap.get(sub.length()));
            }
            if (sub.length() > max) max = sub.length();
        }

        for (; max > 0; max--) {
            if (dictMap.get(max) != null) {
                List<String> l = dictMap.get(max);
                for (String lSub : l) {
                    if (checkS1ContainS2(s, lSub)) return lSub;
                }
            }
        }
        return "";
    }

    public static boolean checkS1ContainS2(String s1, String s2) {
        int i = 0, j = 0;
        for (; i < s2.length() && j != -1; i++) {
            j = s1.indexOf(s2.charAt(i), j);
            if(j != -1) j++;
        }
        return i == s2.length() && j != -1;
    }

    public static void putIntoOrderList(String s, List<String> d) {
        String head = d.get(0);
        int compare = s.compareTo(head);
        if (compare == 0) d.add(s);
        else if (compare < 0) d.add(0, s);
        else d.add(s);
        d.add(s);
    }
}
