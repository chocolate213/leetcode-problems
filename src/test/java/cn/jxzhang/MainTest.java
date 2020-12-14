package cn.jxzhang;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class MainTest {

    /**
     * 2020-12-15
     *
     * 49. 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     */
    @Test
    public void testGroupAnagrams() {
        String[] str = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = groupAnagrams(str);
        List<List<String>> lists2 = groupAnagrams2(str);
        System.out.println(lists);
        System.out.println(lists2);
    }

    private List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedString = new String(chars);

            List<String> strings = map.getOrDefault(sortedString, new ArrayList<>());
            strings.add(str);
            map.put(sortedString, strings);
        }

        return new ArrayList<>(map.values());
    }


    private List<List<String>> groupAnagrams2(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(str -> {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        })).values());
    }
}
