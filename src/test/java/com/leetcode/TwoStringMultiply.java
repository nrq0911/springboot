package com.leetcode;

public class TwoStringMultiply {

    public static void main(String[] args) {
        System.out.println(multiply("12", "12"));
        System.out.println(multiply("99", "99"));
    }

    public static String multiply(String num1, String num2) {
        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;

        int [] mul = new int[len1 + len2 + 2];
        for (int i = len1; i >= 0; i--) {
            for (int j = len2; j >= 0; j--) {
                int a = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + mul[i+j+1];
                mul[i+j] += a /10;
                mul[i+j+1] = a % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        // 去掉前导0
        while(i < mul.length-1 && mul[i] == 0)
            i++;
        for(; i < mul.length; ++i)
            sb.append(mul[i]);
        return sb.toString();
    }

}
