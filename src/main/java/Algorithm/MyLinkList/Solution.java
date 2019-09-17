package Algorithm.MyLinkList;

import java.util.*;

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


    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = null;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            pre = slow;
            slow = slow.next;
        }
        if (pre != null) {
            pre.next = slow.next;
        } else {
            return slow.next;
        }
        return head;
    }

    public int findContentChildren(int[] g, int[] s) {
        if (g.length == 0 || s.length == 0) {
            return 0;
        }
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int pos = 0;

        for (int i = 0; i < s.length; i++) {
            if (pos >= g.length) {
                break;
            }
            if (g[pos] <= s[i]) {
                count++;
                pos++;
            }
        }
        return count;
    }

    private ListNode reverseList(ListNode start) {
        if (start == null || start.next == null) {
            return start;
        }
        ListNode headNew = reverseList(start.next);
        start.next.next = start;
        start.next = null;
        return headNew;
    }

    //    merge sort
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;
        return mergeTwoLists(sortList(head), sortList(mid));
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        //Find the middle of the list
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }

        //Reverse the half after middle  1->2->3->4->5->6 to 1->2->3->6->5->4
        ListNode preMiddle = p1;
        ListNode preCurrent = p1.next;
        // while(preCurrent.next!=null){
        //     ListNode current=preCurrent.next;
        //     preCurrent.next=current.next;
        //     current.next=preMiddle.next;
        //     preMiddle.next=current;
        // }
        preMiddle.next = reverseList(preCurrent);

        //Start reorder one by one  1->2->3->6->5->4 to 1->6->2->5->3->4
        p1 = head;
        p2 = preMiddle.next;
        while (p1 != preMiddle) {
            preMiddle.next = p2.next;
            p2.next = p1.next;
            p1.next = p2;
            p1 = p2.next;
            p2 = preMiddle.next;

        }
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);
        for (int i = 0; i < nums.length; i++) {
            if (heap.size() < k) {
                heap.offer(nums[i]);
            } else {
                int top = heap.peek();
                if (top < nums[i]) {
                    heap.poll();
                    heap.offer(nums[i]);
                }
            }

        }
        return heap.peek();
    }
}
