package com.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SimplifyPath {

    public static void main(String[] args) {
        //System.out.println(simplifyPath("/a/./b/../../c/"));
        //System.out.println(simplifyPath("/home//foo/"));
        //System.out.println(simplifyPath("/../"));
        //System.out.println(simplifyPath("/a//b////c/d//././/.."));
        //System.out.println(simplifyPath("/a/../../b/../c//.//"));
        System.out.println(simplifyPath("/"));
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();



        return result;
    }

    public static String simplifyPath(String path) {
        if (null == path || "".equals(path)) return "";
        Stack<String> stack = new Stack<>();
        stack.push("/");
        String [] strs = path.split("/");
        for (String s : strs) {
            if ("..".equals(s)) {
                if (!stack.empty()) stack.pop();
            } else if (".".equals(s) || "".equals(s)) {
                continue;
            } else {
                stack.push(s + "/");
            }
        }
        if (stack.empty()) {
            return  "/";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : stack) {
            stringBuilder.append(s);
        }
        if (stringBuilder.charAt(0) != '/') stringBuilder.insert(0,"/");
        return stringBuilder.length() == 1 ? stringBuilder.toString() : stringBuilder.substring(0, stringBuilder.length()-1);
    }

}
