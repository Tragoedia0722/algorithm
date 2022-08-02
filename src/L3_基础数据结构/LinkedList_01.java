package L3_基础数据结构;

import java.util.Objects;

/**
 * 单链表反转
 */
public class LinkedList_01 {
    public static void main(String[] args) {
        int maxLength = 1000;
        int maxRange = 100;
        int times = 100;
        compare(maxLength, maxRange, times);
    }

    /**
     * 结点结构
     */
    private static class Node<T> {
        private T value;
        private Node<T> next;

        private int length;

        public Node() {
        }

        public Node(T data) {
            value = data;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return length == node.length && value.equals(node.value) && next.equals(node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, next, length);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    /**
     * 结点添加
     */
    public static <T> void add(Node<T> head, T data) {
        head.setLength(head.getLength() + 1);
        // 头结点为空
        if (head.getValue() == null) {
            head.setValue(data);
            return;
        }

        // 尾部追加
        Node<T> node = head;
        while (node.getNext() != null) {
            node = node.getNext();
        }
        node.setNext(new Node<>(data));
    }

    /**
     * 暴力法
     * 遍历单链表 node，将值依次存入辅助数组 arr 中
     * 反向遍历辅助数组 arr，将数值依次添加到新单链表 ans 中
     * 返回新单链表 ans
     */
    public static <T> Node<Integer> methodA(Node<T> head) {
        if (head.getValue() == null) {
            return new Node<>();
        }

        Node<Integer> ans = new Node<>();
        int[] arr = new int[head.getLength()];

        int i = 0;
        while (head.getNext() != null) { // 遍历单链表获取值
            arr[i] = (int) head.getValue();
            head = head.getNext();
            i++;
        }
        arr[i] = (int) head.getValue(); // 链表最后一个数值

        for (int j = arr.length - 1; j >= 0; j--) {
            add(ans, arr[j]);
        }

        return ans;
    }

    /**
     * 三指针法
     * 将指针 p 指向头结点，指针 pre 指向空
     * 遍历结点，若当前指针 p 不为空
     * 将指针 q 指向 p 指针的下一位，记录为下一位的结点位置
     * 将 pre 指针的值赋予 p 的下一位，达到链反转效果
     * 将指针 pre 指向指针 p，记录 pre 为上一位结点位置
     * 将指针 p 指向指针 q，此时一轮反转操作完成
     * 当 p 指针为空时，遍历完成，此时 p q 结点均指向原尾结点 null，pre 指向原最后一个数值结点
     * 返回 pre 为反转链表的头结点
     */
    public static <T> Node<T> methodB(Node<T> head) {
        // 头结点为空
        if (head.getValue() == null) {
            return new Node<>();
        }

        Node<T> pre = null;
        Node<T> p = head;
        Node<T> q;

        // 头结点非空
        while (p != null) {
            q = p.getNext();
            p.setNext(pre);
            pre = p;
            p = q;
        }

        return pre;
    }

    /**
     * 对数器
     */
    public static void compare(int maxLength, int maxRange, int times) {
        for (int i = 0; i < times; i++) {
            Node<Integer> head = generateRandomLinkedList(maxLength, maxRange);
            Node<Integer> methodA = methodA(head);
            Node<Integer> methodB = methodB(head);
            if (!methodA.toString().equals(methodB.toString())) {
                System.out.println("head = " + head);
                System.out.println("methodA(head) = " + methodA);
                System.out.println("methodB(head) = " + methodB);
                break;
            }
        }
        System.out.println("通过");
    }

    /**
     * 随机生成单链表
     */
    public static Node<Integer> generateRandomLinkedList(int maxLength, int maxRange) {
        int length = (int) (Math.random() * (maxLength + 1));

        Node<Integer> node = new Node<>();
        for (int i = 0; i < length; i++) {
            int value = (int) (Math.random() * (maxRange + 1)) - (int) (Math.random() * (maxRange + 1));
            add(node, value);
        }
        return node;
    }

}


