package Algorithm.MyLRU;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class Node {
        Node pre;
        Node next;
        Integer key;
        Integer value;

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    int cap;
    Map<Integer, Node> map = new HashMap<>();
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.cap = capacity;
        head = new Node(null, null);
        tail = new Node(null, null);
        head.next = tail;
        tail.pre = head;
    }

    public Node getKey(int key) {
        Node n = map.get(key);
        if (n != null) {
            n.pre.next = n.next;
            n.next.pre = n.pre;
            appendTail(n);
        }
        return n;
    }

    public void setKV(int key, int value) {
        Node n = map.get(key);
        if (n != null) {
            n.value = value;
            map.put(key, n);
            n.pre.next = n.next;
            n.next.pre = n.pre;
            appendTail(n);
            return;
        }
        if (map.size() >= cap) {
            // 满了，先去掉头结点数据
            Node tmp = head.next;
            head.next = head.next.next;
            head.next.pre = head;
            map.remove(tmp.key);
        }
        n = new Node(key, value);
        appendTail(n);
        map.put(key, n);
        return;
    }

    public void appendTail(Node n) {
        tail.pre.next = n;
        n.next = tail;
        n.pre = tail.pre;
        tail.pre = n;
    }

}
