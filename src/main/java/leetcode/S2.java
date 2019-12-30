package leetcode;

import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}


public class S2 {
    ListNode listToListNode(int[] input) {
        ListNode head = new ListNode(-1);
        ListNode now = head;
        for (int n : input) {
            now.next = new ListNode(n);
            now = now.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        S2 s2 = new S2();
        ListNode l1 = s2.listToListNode(new int[]{1, 8});
        ListNode l2 = s2.listToListNode(new int[]{0});
        ListNode res = s2.addTwoNumbers(l1, l2);
        System.out.println("");
    }

    private ListNode reverseListNode(ListNode n) {
        if (n == null || n.next == null) {
            return n;
        }
        ListNode tmp = n.next;
        ListNode headNew = reverseListNode(n.next);
        tmp.next = n;
        n.next = null;
        return headNew;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode head = new ListNode(0);
        ListNode p = head;
        int tmp = 0;
        while (l1 != null || l2 != null || tmp != 0) {
            if (l1 != null) {
                tmp += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                tmp += l2.val;
                l2 = l2.next;
            }

            p.next = new ListNode(tmp % 10);
            p = p.next;
            tmp = tmp / 10;
        }
        return head.next;
    }
}
