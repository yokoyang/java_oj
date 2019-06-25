package Algorithm.MyLinkList;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {

    public ListNode mergeKLists2(ListNode[] lists) {
        ListNode dummy = new ListNode(0), cur = dummy;
        if (lists == null || lists.length < 1) {
            return null;
        }
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, Comparator.comparingInt(l -> l.val));
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                minHeap.offer(lists[i]);
            }
        }
        while (!minHeap.isEmpty()) {
            ListNode temp = minHeap.poll();
            cur.next = temp;
            if (temp.next != null) {
                minHeap.offer(temp.next);
            }
            cur = temp;
        }
        return dummy.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(0);
        ListNode pre = head;

        Set<Integer> record = new HashSet<>();
        for (int i = 0; i < lists.length; i++) {
            record.add(i);
        }

        int index = 0;
        while (record.size() >= 1) {
            int nowMin = Integer.MAX_VALUE;
            for (int i = 0; i < lists.length; i++) {
                ListNode node = lists[i];
                if (node == null) {
                    record.remove(i);
                    continue;
                }
                if (node.val < nowMin) {
                    nowMin = node.val;
                    index = i;
                }
            }
            if (lists[index] != null) {
                System.out.println(lists[index].val);
                ListNode now = new ListNode(lists[index].val);
                pre.next = now;
                pre = now;
                lists[index] = lists[index].next;
            }

        }
        for (Integer p : record) {
            pre.next = lists[p];
            break;
        }
        return head.next;
    }

    private ListNode reverseLinkedList(ListNode node) {
        if (node == null) {
            return node;
        }
        ListNode pre = node;
        ListNode now = node.next;
        ListNode tmp;
        while (now != null) {
            tmp = now.next;
            now.next = pre;
            pre = now;
            now = tmp;
        }
        node.next = null;
        return pre;
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[3];

        lists[0] = new ListNode(1);
        lists[0].next = new ListNode(4);
        lists[0].next.next = new ListNode(5);

        lists[1] = new ListNode(1);
        lists[1].next = new ListNode(3);
        lists[1].next.next = new ListNode(4);

        lists[2] = new ListNode(2);
        lists[2].next = new ListNode(6);
        new Solution().mergeKLists(lists);
    }
}
