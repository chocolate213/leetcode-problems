package cn.jxzhang;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class MainTest {

    /**
     * 49. 字母移位词分组
     */
    @Test
    public void groupAnagrams() {
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

    /**
     * 738. 单调递增的数字
     */
    @Test
    public void monotoneIncreasingDigits() {
        int i = monotoneIncreasingDigits(322);
        Assert.assertEquals(i, 299);
    }

    private int monotoneIncreasingDigits(int N) {
        int i = 1;
        int res = N;
        while (i <= res / 10) {
            int n = res / i % 100; // 每次取两个位
            i *= 10;
            if (n / 10 > n % 10) // 比较的高一位大于底一位
                res = res / i * i - 1; //例如1332 循环第一次变为1330-1=1329 第二次变为1300-1=1299
        }
        return res;
    }

    /**
     * 290. 单词规律
     */
    @Test
    public void wordPattern() {
        Assert.assertTrue(wordPattern("abba", "dog cat cat dog"));
    }

    private boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        char[] patterns = pattern.toCharArray();

        if (words.length != patterns.length) {
            return false;
        }

        Map<Character, String> ch2str = new HashMap<>();
        Map<String, Character> str2ch = new HashMap<>();

        for(int i = 0; i < words.length; i++) {
            char key = patterns[i];
            String value = words[i];

            if (ch2str.containsKey(key) && !value.equals(ch2str.get(key))) {
                return false;
            }

            if (str2ch.containsKey(value) && key != str2ch.get(value)) {
                return false;
            }

            ch2str.put(key, value);
            str2ch.put(value, key);
        }

        return true;
    }
}

