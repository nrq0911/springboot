package com.leetcode;

public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("bbb"));
    }

    public static String longestPalindrome(String s) {
        if (null == s || s.length() == 0) return "";
        int start = 0, end = 0;
        for (int i = 0; i< s.length(); i++) {
            int len1 = findLongestPalindrome(s, i, i);  // 单核
            int len2 = findLongestPalindrome(s, i, i + 1);  // 双核
            int len = Math.max(len1,len2);
            if (len > end - start) {
                start = i - (len -1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int findLongestPalindrome (String s, int left, int right) {
        int l = left, r = right;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }

}
