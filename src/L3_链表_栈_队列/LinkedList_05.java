package L3_链表_栈_队列;

import java.util.ArrayList;
import java.util.List;

/**
 * 双链表实现栈
 */
public class LinkedList_05 {
    public static void main(String[] args) {
        DoublyLNode<Integer> doublyLNode = new DoublyLNode<>();
        push(doublyLNode, 1); // 1
        push(doublyLNode, 2); // 1 2
        push(doublyLNode, 3); // 1 2 3
        System.out.println("pop(doublyLNode) = " + pop(doublyLNode));  // 1 2
        System.out.println("pop(doublyLNode) = " + pop(doublyLNode));  // 1
        System.out.println("pop(doublyLNode) = " + pop(doublyLNode));  // null
        System.out.println("pop(doublyLNode) = " + pop(doublyLNode));  // exception
    }

    /**
     * 入栈
     * 将数值 value 放到指针 head 所指的双链表的尾部
     */
    public static <T> void push(DoublyLNode<T> head, T data) {
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
     * 出栈
     * 若当前指针 head 指向为双链表的头结点，则出栈后 head 指向空
     * 若当前指针 head 指向双链表不为头结点，则将该双链表的尾结点出栈
     */
    public static <T> T pop(DoublyLNode<T> head) {
        head.setLength(head.getLength() - 1);
        T ans = null;
        if (head.getValue() == null) {
            throw new RuntimeException("栈为空");
        }

        if (head.getNext() == null) {
            ans = head.getValue();
            head.setPrev(null);
            head.setValue(null);
            return ans;
        }

        DoublyLNode<T> prev = new DoublyLNode<>();
        while (head != null) {
            ans = head.getValue();
            prev = head;
            head = head.getNext();
        }
        prev.getPrev().setNext(null);
        return ans;
    }

    /**
     * 栈顶元素
     */
    public static <T> T peek(DoublyLNode<T> head) {
        T ans = null;
        while (head != null && head.getValue() != null) {
            ans = head.getValue();
            head = head.getNext();
        }
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
