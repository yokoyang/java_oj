package Algorithm.MyLinkList;

import java.util.LinkedList;

public class MyCircularDeque {
//    public int[] maxSlidingWindow(int[] nums, int k) {
//        LinkedList<Integer> deque = new LinkedList<>();
//        for (int i = 0; i < nums.length; i++) {
//
//        }
//    }

    int[] queue;
    int front, tail;
    int count;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public MyCircularDeque(int k) {
        queue = new int[k];
        front = -1;
        tail = -1;
        count = 0;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        front = (front - 1 + queue.length) % queue.length;
        if (isEmpty()) {
            tail = front;
        }
        queue[front] = value;
        count++;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        tail = (tail + 1) % queue.length;
        if (isEmpty()) {
            front = tail;
        }
        queue[tail] = value;
        count++;
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % queue.length;
        count--;
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        tail = (tail - 1 + queue.length) % queue.length;
        count--;
        return true;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        return isEmpty() ? -1 : queue[front];
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        return isEmpty() ? -1 : queue[tail];
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return count == queue.length;
    }

    public static void main(String[] args) {
        MyCircularDeque myCircularDeque = new MyCircularDeque(5);
        myCircularDeque.insertFront(1);
        myCircularDeque.insertFront(2);
        myCircularDeque.insertFront(3);
        myCircularDeque.insertLast(4);
        myCircularDeque.insertLast(112);
        int i = 1;
        System.out.println(--i);
    }
}
