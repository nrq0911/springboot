package com.leetcode;

import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        LengthOfLongestSubstring substring = new LengthOfLongestSubstring();
        System.out.println(substring.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(substring.lengthOfLongestSubstring("bbbbb"));
        System.out.println(substring.lengthOfLongestSubstring("pwwkew"));

        System.out.println(substring.findLongestPalindrome("bb", 1, 2));
    }

    private int findLongestPalindrome (String s, int left, int right) {
        int l = left, r = right;
        while (l > 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l -1;
    }

    public int lengthOfLongestSubstring(String s) {
        Set<Character> chars = new HashSet<>();
        int result = 0;
        char [] a = s.toCharArray();
        String str = "";

        for (char c : a) {
            if (chars.contains(c)) {
                str = removeFirst(str, chars, c);
            }
            str += c;
            chars.add(c);
            result = result > str.length() ? result : str.length();
        }
        return result;

    }

    private String removeFirst(String str, Set<Character> chars, char c) {
        char removeChar = str.charAt(0);
        str = str.substring(1, str.length());
        chars.remove(removeChar);
        if (chars.contains(c)) {
            str = removeFirst(str, chars, c);
        }
        return str;
    }

}
