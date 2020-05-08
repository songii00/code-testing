package com.example.codetesting.leetcode;

import com.example.codetesting.ProgrammersApplication;

/**
 * 리트코드 add-two-numbers
 * https://leetcode.com/problems/add-two-numbers/
 */
public class AddTwoNumbers {

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1==null &&l2==null){
            return null;
        }

        int val1 = l1!=null ? l1.val : 0;
        int val2 = l2!=null ? l2.val : 0;
        int sum = val1 + val2;
        if(sum>=10){ // 10이 넘어갈 경우
            sum = sum%10; // 끝 한자리
            if(l1!=null && l1.next!=null){
                l1.next.val = l1.next.val+1;
            }
            else if(l2!=null && l2.next!=null){
                l2.next.val = l2.next.val+1;
            }else {
                l1 = new ListNode();
                l1.next =  new ListNode(1);
            }
        }

        return new ListNode(sum, addTwoNumbers(l1!=null ? l1.next : null, l2!=null ? l2.next : null));
    }
}
