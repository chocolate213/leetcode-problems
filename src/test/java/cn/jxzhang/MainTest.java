package cn.jxzhang;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class MainTest {

    @Test
    public void testBinarySearch() {
        int[] arr = {1, 2, 3, 4, 5};
        int i = binarySearch(arr, 22);
        System.out.println(i);

        int i1 = binarySearch(arr, 22, 0, 4);
        System.out.println(i1);
    }

    private int binarySearch(int[] arr, int key) {
        int len = arr.length;
        int low = 0, high = len - 1;

        while (low <= high) {
            int mid = (high + low) / 2;
            if (arr[mid] == key) return mid;
            else if (arr[mid] > key)  high = mid - 1;
            else low = mid + 1;
        }

        return -1;
    }

    private int binarySearch(int[] arr, int key, int low, int high) {
        if (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                return binarySearch(arr, key, mid + 1, high);
            } else {
                return binarySearch(arr, key, low, mid - 1);
            }
        } else {
            return -1;
        }
    }

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

    /**
     * 获取倒数第 k 个元素
     */
    @Test
    public void lastKeyNode() {
        LinkNode<Integer> node1 = new LinkNode<>(1);
        LinkNode<Integer> node2 = new LinkNode<>(2);
        LinkNode<Integer> node3 = new LinkNode<>(3);
        LinkNode<Integer> node4 = new LinkNode<>(4);
        LinkNode<Integer> node5 = new LinkNode<>(5);
        LinkNode<Integer> node6 = new LinkNode<>(6);
        LinkNode<Integer> node7 = new LinkNode<>(7);
        LinkNode<Integer> node8 = new LinkNode<>(8);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node7);
        node7.setNext(node8);

        LinkNode<Integer> lastKeyNode = getLastKeyNode(node1, 9);
        System.out.println(lastKeyNode);
    }

    private <T> LinkNode<T> getLastKeyNode(LinkNode<T> linkList, int n) {
        if (linkList == null || n <= 0) {
            return null;
        }

        LinkNode<T> current = linkList;
        for (int i = 0; i < n - 1; i++) {
            if (current == null) {
                return null;
            }
            current = current.next;
        }

        if (current == null) {
            return null;
        }

        LinkNode<T> result = linkList;
        while (current.next != null) {
            current = current.next;
            result = result.next;
        }

        return result;
    }

    private static final class LinkNode<T> {

        private T data;

        private LinkNode<T> next;

        public LinkNode(T data) {
            this.data = data;
        }

        public void setNext(LinkNode<T> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "LinkNode{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }
}

