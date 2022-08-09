package L3_链表_栈_队列;

import java.util.ArrayList;
import java.util.List;

/**
 * 双链表删除特定值
 */
public class LinkedList_03 {
    public static void main(String[] args) {
        int maxLength = 1000;
        int maxRange = 100;
        int times = 1000;
        compare(maxLength, maxRange, times);
    }

    /**
     * 暴力法
     * 遍历双链表 head，将值依次存入辅助数组 arr 中
     * 遍历 arr，将特定值 x 以外的数值添加到新单链表 ans 中
     * 返回新单链表 ans
     */
    public static DoublyLNode<Integer> methodA(DoublyLNode<Integer> head, int x) {
        DoublyLNode<Integer> ans = new DoublyLNode<>();
        int[] arr = new int[head.getLength()];
        int i = 0;
        while (head.getNext() != null) {
            arr[i] = head.getValue();
            head = head.getNext();
            i++;
        }
        if (head.getValue() != null) {
            arr[i] = head.getValue();
        }
        for (int j : arr) {
            if (j != x) {
                add(ans, j);
            }
        }
        return ans;
    }

    /**
     * 指针法
     * 将当前指针 p 和下一位指针 q 分别指向表头结点 head
     * 遍历双向链表
     * 若头结点 head 为待删除的数值 x，则将 head 结点后移
     * 判断当前指针 p 指向的结点的值是否为数值 x，若为数值 x 则将 q 指针结点的下一位指向 p 指针结点的下一位，实现跳过 p 结点
     * 若数值不相等，则将 q 指向 p，此时两指针指向同一结点
     * 每次数值判断后将 p 指针向后移
     * 返回头结点 head
     */
    public static DoublyLNode<Integer> methodB(DoublyLNode<Integer> head, int x) {
        DoublyLNode<Integer> p = head;
        DoublyLNode<Integer> q = p;

        while (p != null) {
            // 删除为头结点
            if (head != null && head.getValue() != null && head.getValue().equals(x)) {
                head = head.getNext();
                if (head != null) { // 非尾结点
                    head.setPrev(null);
                }
            }

            // 删除非头结点
            if (p.getValue() != null && p.getValue().equals(x)) {
                if (p.getNext() != null) { // 非尾结点
                    p.getNext().setPrev(q);
                }
                q.setNext(p.getNext());
            } else {
                q = p;
            }
            p = p.getNext();
        }
        return head;
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
            int xRange = (int) (Math.random() * (doublyLNode.getLength()));
            List<Integer> list = toList(doublyLNode);
            int x = 0;
            if (list.size() > 0 && list.get(xRange) != null) {
                x = list.get(xRange);
            }
            DoublyLNode<Integer> methodA = methodA(doublyLNode, x);
            DoublyLNode<Integer> methodB = methodB(doublyLNode, x);
            List<Integer> listA = toList(methodA);
            List<Integer> listB = toList(methodB);
            for (int j = 0; j < listA.size() - 1; j++) {
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
        if (head != null) {
            while (head.getNext() != null) {
                list.add(head.getValue());
                head = head.getNext();
            }
            list.add(head.getValue());
        }
        return list;
    }
}
