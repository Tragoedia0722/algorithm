package L3_链表_栈_队列;

import java.util.ArrayList;
import java.util.List;

/**
 * 双链表反转
 */
public class LinkedList_02 {
    public static void main(String[] args) {
        int maxLength = 1000;
        int maxRange = 100;
        int times = 100;
        compare(maxLength, maxRange, times);
    }

    /**
     * 暴力法
     * 遍历双链表 DoublyLNode，将值依次存入辅助数组 arr 中
     * 反向遍历辅助数组 arr，将数值依次添加到新双链表 ans 中
     * 返回新单链表 ans
     */
    public static DoublyLNode<Integer> methodA(DoublyLNode<Integer> head) {
        DoublyLNode<Integer> ans = new DoublyLNode<>();
        int[] arr = new int[head.getLength()];
        int i = 0;
        while (head.getNext() != null) {
            arr[i] = head.getValue();
            head = head.getNext();
            i++;
        }
        arr[i] = head.getValue();
        for (int j = arr.length - 1; j >= 0; j--) {
            add(ans, arr[j]);
        }
        return ans;
    }

    /**
     * 指针法
     * 将指针 p 指向头结点，pre指向空
     * 遍历结点，若当前指针不为空
     * 将指针 q 指向 p 的 next 结点，记录为下一位的结点位置
     * 将 p 的 next 指向 pre，达到链表反转的效果
     * 将 pre 的 prev 指向 p，使前后结点链接正确
     * 将 pre 指向 p，记录 pre 为上一位的结点位置
     * 将 p 指向 q，此时一次反转操作完成
     * 当 p 指向空时遍历完成，此时 p q 均指向原尾结点 null，pre 指向原来最后一个数值结点
     * 返回 pre 为反转结点的表头结点
     */
    public static DoublyLNode<Integer> methodB(DoublyLNode<Integer> head) {
        DoublyLNode<Integer> pre = null;
        DoublyLNode<Integer> p = head;
        DoublyLNode<Integer> q;

        while (p != null) {
            q = p.getNext();
            p.setNext(pre);
            if (pre != null) {
                pre.setPrev(p);
            }
            pre = p;
            p = q;
        }
        return pre;
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
     * 添加双向链表结点
     */
    public static <T> void add(DoublyLNode<T> head, T data) {
        head.setLength(head.getLength() + 1);
        // 头结点为空
        if (head.getValue() == null) {
            head.setValue(data);
            return;
        }
        // 添加前后结点
        DoublyLNode<T> prev = new DoublyLNode<>();
        DoublyLNode<T> next = new DoublyLNode<>(data);
        while (head.getNext() != null) { // 进入最后一个结点
            prev = head;
            head = head.getNext();
        }
        head.setNext(next);
        head.setPrev(prev);
        head.getNext().setPrev(head);
    }

    /**
     * 对数器
     */
    public static void compare(int maxLength, int maxRange, int times) {
        for (int i = 0; i < times; i++) {
            DoublyLNode<Integer> doublyLNode = generateRandomDoublyLinkedList(maxLength, maxRange);
            DoublyLNode<Integer> methodA = methodA(doublyLNode);
            DoublyLNode<Integer> methodB = methodB(doublyLNode);
            List<Integer> listA = toList(methodA);
            List<Integer> listB = toList(methodB);
            for (int j = 0; j < listA.size(); j++) {
                if (!listA.get(j).equals(listB.get(j))) {
                    System.out.println("doublyLNode = " + doublyLNode);
                    System.out.println("methodA = " + methodA);
                    System.out.println("methodB = " + methodB);
                    break;
                }
            }

        }
        System.out.println("通过");
    }


    /**
     * 随机生成双链表
     */
    public static DoublyLNode<Integer> generateRandomDoublyLinkedList(int maxLength, int maxRange) {
        int length = (int) (Math.random() * (maxLength + 1));

        DoublyLNode<Integer> doublyLNode = new DoublyLNode<>();
        for (int i = 0; i < length; i++) {
            int value = (int) (Math.random() * (maxRange + 1)) - (int) (Math.random() * (maxRange + 1));
            add(doublyLNode, value);
        }
        return doublyLNode;
    }


    /**
     * 链表转列表
     */
    public static List<Integer> toList(DoublyLNode<Integer> head) {
        List<Integer> list = new ArrayList<>();
        while (head.getNext() != null) {
            list.add(head.getValue());
            head = head.getNext();
        }
        list.add(head.getValue());
        return list;
    }


}
