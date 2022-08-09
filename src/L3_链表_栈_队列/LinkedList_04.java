package L3_链表_栈_队列;

import java.util.ArrayList;
import java.util.List;

/**
 * 双链表实现队列
 */
public class LinkedList_04 {
    public static void main(String[] args) {
        DoublyLNode<Integer> doublyLNode = new DoublyLNode<>();
        offer(doublyLNode, 1); // 1
        offer(doublyLNode, 2); // 1 2
        offer(doublyLNode, 3); // 1 2 3
        System.out.println("poll(doublyLNode) = " + poll(doublyLNode)); // 2 3
        System.out.println("poll(doublyLNode) = " + poll(doublyLNode)); // 3
        System.out.println("poll(doublyLNode) = " + poll(doublyLNode)); // null
        // System.out.println("poll(doublyLNode) = " + poll(doublyLNode)); // exception

        offer(doublyLNode, 1);
        offer(doublyLNode, 1);
        offer(doublyLNode, 1);
        System.out.println("toList(doublyLNode) = " + toList(doublyLNode));
    }

    /**
     * 入队
     * 将数值 value 放到指针 head 所指的双链表的尾部
     */
    public static <T> void offer(DoublyLNode<T> head, T data) {
        head.setLength(head.getLength() + 1);
        if (head.getValue() == null) {
            head.setValue(data);
            return;
        }
        DoublyLNode<T> prev = new DoublyLNode<>();
        while (head != null) {
            prev = head;
            head = head.getNext();
        }
        head = new DoublyLNode<>(data);
        prev.setNext(head);
        head.setPrev(prev);
    }

    /**
     * 出队
     * 队头出队
     */
    public static <T> T poll(DoublyLNode<T> head) {
        if (head.getValue() == null) {
            throw new RuntimeException("队列为空");
        }

        T ans;
        if (head.getNext() == null) {
            ans = head.getValue();
            head.setValue(null);
            head.setPrev(null);
            head.setNext(null);
            return ans;
        }
        ans = head.getValue();
        DoublyLNode<T> next = head.getNext();
        head.setPrev(next.getPrev());
        head.setNext(next.getNext());
        head.setValue(next.getValue());

        return ans;
    }

    /**
     * 结点结构
     */
    public static class DoublyLNode<T> {
        private T value;
        private DoublyLNode<T> prev;
        private DoublyLNode<T> next;
        private int length;

        public DoublyLNode() {

        }

        public DoublyLNode(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public DoublyLNode<T> getPrev() {
            return prev;
        }

        public void setPrev(DoublyLNode<T> prev) {
            this.prev = prev;
        }

        public DoublyLNode<T> getNext() {
            return next;
        }

        public void setNext(DoublyLNode<T> next) {
            this.next = next;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }
    }

    /**
     * 链表转列表
     */
    public static List<Integer> toList(DoublyLNode<Integer> head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.getValue());
            head = head.getNext();
        }
        return list;
    }
}
