package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Partition131 {

    public static void main(String[] args) {
        Partition131 partition = new Partition131();
        List<List<String>> result = partition.partition("aab");
        System.out.println(result);
    }


    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(result, s, new ArrayList<>());
        return result;
    }

    private void backtrack(List<List<String>> result, String s, List<String> tmp) {
        if (s == null || s.length() == 0) result.add(new ArrayList<>(tmp));

        for (int i = 1; i <= s.length(); i++) {
            if (isPalindrome(s,0, i-1)) {
                System.out.println(s.substring(0, i));
                tmp.add(s.substring(0, i));
                backtrack(result, s.substring(i), tmp);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String str, int start, int end) {
        while(start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

}
