package com.leetcode;

public class AddTwoNumber {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
        l1.next = new ListNode(5);
        ListNode l2 = new ListNode(5);

        System.out.println(new Solution().addTwoNumbers(l1,l2));

    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    static class Solution {

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode p = l1, q = l2,result = new ListNode(0);
            ListNode current = result;
            int x = 0;
            while(p != null || q!= null) {
                int a = (p == null) ? 0 : p.val;
                int b = (q == null) ? 0 : q.val;
                int val = a + b + x;

                x = val / 10;
                current.next = new ListNode(val%10);

                p = (p == null) ? null : p.next;
                q = (q == null) ? null : q.next;

                current = current.next;
            }
            if (x > 0) {
                current.next = new ListNode(x);
            }
            return result.next;
        }

    }




}
