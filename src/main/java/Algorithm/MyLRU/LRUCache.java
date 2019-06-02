package Algorithm.MyLRU;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private class Node {
        Node pre;
        Node next;
        int key;
        int value;

        private Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    Map<Integer, Node> map = new HashMap<>();
    Node head;
    Node tail;
    int cap;

    public LRUCache(int c) {
        cap = c;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.pre = head;
    }

    public int LRUGet(int key) {
        Node node = map.get(key);
        if (node != null) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            appendTail(node);
            return node.value;
        }
        return -1;
    }

    public int LRUSet(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            map.put(key, node);
            node.pre.next = node.next;
            node.next.pre = node.pre;
            appendTail(node);
            return node.value;
        } else if (map.size() >= cap) {
            Node tmp = head.next;
            head.next = head.next.next;
            head.next.pre = head;
            map.remove(tmp.key);
        }
        node = new Node(key,value);
        appendTail(node);
        map.put(key,node);
        return node.value;
    }

    private void appendTail(Node n) {
        n.next = tail;
        n.pre = tail.pre;
        tail.pre.next = n;
        tail.pre = n;
    }
}
