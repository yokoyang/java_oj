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
    public ListNode array2ListNode(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        ListNode head = new ListNode(nums[0]), pre = head;

        if (nums.length == 1) {
            return new ListNode(nums[0]);
        }
        for (int i = 1; i < nums.length; i++) {
            ListNode now = new ListNode(nums[i]);
            pre.next = now;
            pre = now;
        }
        return head;
    }

    //    Given a singly linked list, determine if it is a palindrome.
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head, fast = head, pre = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }
        if (pre != null) {
            pre.next = null;
        }
        ListNode newNode = reverseLinkedList(slow);
        while (head != null && newNode != null) {
            if (head.val != newNode.val) {
                return false;
            }
            head = head.next;
            newNode = newNode.next;
        }
        return true;
    }

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
        Solution s = new Solution();
        s.deleteDuplication(s.array2ListNode(new int[]{1, 1, 1, 2}));
        ListNode h = s.array2ListNode(new int[]{1, 2, 2, 1});
        s.isPalindrome(h);
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

//        Arrays.sort(g, Collections.reverseOrder());
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int pos = 0;

        for (int value : s) {
            if (pos >= g.length) {
                break;
            }
            if (g[pos] <= value) {
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

    private ListNode reverseListNode(ListNode n) {
        if (n == null || n.next == null) {
            return n;
        }
        ListNode nextNode = n.next;
        ListNode newHead = reverseListNode(nextNode);
        nextNode.next = n;
        n.next = null;
        return newHead;
    }

    //对原始链表修改了，翻转后打印，不推荐
    // 方法2：用栈来实现，保持原始数据不变
    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<>();
        if (listNode == null) {
            return res;
        }
        ListNode newHead = reverseListNode(listNode);
        while (newHead != null) {
            res.add(newHead.val);
            newHead = newHead.next;
        }
        return res;
    }

    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<>();
        if (listNode == null) {
            return res;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }

    private long convertListNode2Num(ListNode n) {
        long num = 0;
        while (n != null) {
            num *= 10;
            num += n.val;
            n = n.next;
        }
        return num;
    }

    private ListNode num2ListNode(long num) {

        LinkedList<Long> integerList = new LinkedList<>();
        if (num == 0) {
            return new ListNode(0);
        }
        while (num != 0) {
            integerList.offerFirst(num % 10);
            num /= 10;
        }
        ListNode pre = new ListNode(-1);
        ListNode dummy = pre;

        while (integerList.size() > 0) {
            long v = integerList.pollFirst();
            ListNode now = new ListNode((int) v);
            pre.next = now;
            pre = now;
        }
        return dummy.next;
    }

    //    数字太大会超出
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        long num1 = convertListNode2Num(l1);
        num1 += convertListNode2Num(l2);
        return num2ListNode(num1);
    }

    //
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;
        //push numbers to stack so we can start from the lower digit
        Stack<ListNode> s1 = new Stack<>();
        while (l1 != null) {
            s1.push(l1);
            l1 = l1.next;
        }
        Stack<ListNode> s2 = new Stack<>();
        while (l2 != null) {
            s2.push(l2);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode resNode = null;
        //start iterating on stacks until both are empty, keep the carry on part
        while (!s1.isEmpty() || !s2.isEmpty()) {
            int n1 = s1.isEmpty() ? 0 : s1.pop().val;
            int n2 = s2.isEmpty() ? 0 : s2.pop().val;
            int sum = n1 + n2 + carry;
            //create current sum digit, add previous node as next
            ListNode n = new ListNode(sum % 10);
            n.next = resNode;
            //make current node our result
            resNode = n;
            carry = sum / 10;
        }
        //take care of remainder
        if (carry > 0) {
            ListNode n = new ListNode(carry);
            n.next = resNode;
            resNode = n;
        }
        return resNode;
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode now = head;
        while (now != null && now.next != null) {
            ListNode n = now.next;
            now.next = n.next;
            pre.next = n;
            n.next = now;
            pre = now;
            now = now.next;
        }
        return dummy.next;
    }

    //    merge sort
    private ListNode sortList(ListNode head) {
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


    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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

    private ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                ListNode tmp = l1;
                l1 = l2;
                l2 = tmp;
            }
            tail.next = l1;
            l1 = l1.next;
            tail = tail.next;
        }
        tail.next = (l1 != null) ? l1 : l2;
        return dummy.next;
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

    //删除链表中的重复节点
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode Head = new ListNode(0);
        Head.next = pHead;
        ListNode pre = Head;
        ListNode last = Head.next;
        while (last != null) {
            if (last.next != null && last.val == last.next.val) {
                // 找到最后的一个相同节点
                while (last.next != null && last.val == last.next.val) {
                    last = last.next;
                }
                pre.next = last.next;
                last = last.next;
            } else {
                pre = pre.next;
                last = last.next;
            }
        }
        return Head.next;
    }

}
