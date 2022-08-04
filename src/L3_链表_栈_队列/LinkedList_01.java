package L3_链表_栈_队列;

import java.util.ArrayList;
import java.util.List;
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
    private static class LNode<T> {
        private T value;
        private LNode<T> next;

        private int length;

        public LNode() {
        }

        public LNode(T data) {
            value = data;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public LNode<T> getNext() {
            return next;
        }

        public void setNext(LNode<T> next) {
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
     * 暴力法
     * 遍历单链表 LNode，将值依次存入辅助数组 arr 中
     * 反向遍历辅助数组 arr，将数值依次添加到新单链表 ans 中
     * 返回新单链表 ans
     */
    public static <T> LNode<Integer> methodA(LNode<T> head) {
        if (head.getValue() == null) {
            return new LNode<>();
        }

        LNode<Integer> ans = new LNode<>();
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
     * 指针法
     * 将指针 p 指向头结点，指针 pre 指向空
     * 遍历结点，若当前指针 p 不为空
     * 将指针 q 指向 p 指针的下一位，记录为下一位的结点位置
     * 将 pre 指针的值赋予 p 的下一位，达到链反转效果
     * 将指针 pre 指向指针 p，记录 pre 为上一位结点位置
     * 将指针 p 指向指针 q，此时一轮反转操作完成
     * 当 p 指针为空时，遍历完成，此时 p q 结点均指向原尾结点 null，pre 指向原最后一个数值结点
     * 返回 pre 为反转链表的头结点
     */
    public static <T> LNode<T> methodB(LNode<T> head) {
        // 头结点为空
        if (head.getValue() == null) {
            return new LNode<>();
        }

        LNode<T> pre = null;
        LNode<T> p = head;
        LNode<T> q;

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
     * 结点添加
     */
    public static <T> void add(LNode<T> head, T data) {
        head.setLength(head.getLength() + 1);
        // 头结点为空
        if (head.getValue() == null) {
            head.setValue(data);
            return;
        }

        // 尾部追加
        LNode<T> LNode = head;
        while (LNode.getNext() != null) {
            LNode = LNode.getNext();
        }
        LNode.setNext(new LNode<>(data));
    }

    /**
     * 对数器
     */
    public static void compare(int maxLength, int maxRange, int times) {
        for (int i = 0; i < times; i++) {
            LNode<Integer> head = generateRandomLinkedList(maxLength, maxRange);
            LNode<Integer> methodA = methodA(head);
            List<Integer> listA = toList(methodA);
            LNode<Integer> methodB = methodB(head);
            List<Integer> listB = toList(methodB);
            for (int j = 0; j < listA.size(); j++) {
                if (!listA.get(j).equals(listB.get(j))) {
                    System.out.println("head = " + head);
                    System.out.println("methodA(head) = " + methodA);
                    System.out.println("methodB(head) = " + methodB);
                    break;
                }
            }

        }
        System.out.println("通过");
    }

    /**
     * 随机生成单链表
     */
    public static LNode<Integer> generateRandomLinkedList(int maxLength, int maxRange) {
        int length = (int) (Math.random() * (maxLength + 1));

        LNode<Integer> LNode = new LNode<>();
        for (int i = 0; i < length; i++) {
            int value = (int) (Math.random() * (maxRange + 1)) - (int) (Math.random() * (maxRange + 1));
            add(LNode, value);
        }
        return LNode;
    }

    /**
     * 链表转列表
     */
    public static List<Integer> toList(LNode<Integer> head) {
        List<Integer> list = new ArrayList<>();
        while (head.getNext() != null) {
            list.add(head.getValue());
            head = head.getNext();
        }
        list.add(head.getValue());
        return list;
    }

}


