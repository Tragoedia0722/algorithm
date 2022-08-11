package L3_链表_栈_队列;

import java.util.Arrays;

/**
 * 数组实现队列
 */
public class LinkedList_06 {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(5);

        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        myQueue.push(4);
        myQueue.push(5);
        System.out.println("myQueue.pop() = " + myQueue.pop());
        System.out.println("myQueue.pop() = " + myQueue.pop());

        myQueue.push(6);
        myQueue.push(7);

        System.out.println("myQueue = " + myQueue);
    }

    /**
     * 队列结构
     */
    public static class MyQueue {
        private final int[] arr;
        private final int size;
        private int head;
        private int tail;
        private int length;

        public MyQueue(int size) {
            this.arr = new int[size];
            this.head = 0;
            this.tail = 0;
            this.size = size;
            this.length = 0;
        }

        /**
         * 入队
         */
        public void push(int value) {
            if (length == size) {
                throw new RuntimeException("队列已满");
            }
            length++;
            arr[tail] = value;
            tail = getNextIndex(tail);
        }

        /**
         * 出队
         */
        public int pop() {
            if (size == 0) {
                throw new RuntimeException("队列为空");
            }
            length--;
            int ans = arr[head];
            head = getNextIndex(head);
            return ans;
        }

        /**
         * 获取下一索引，防止溢出
         */
        public int getNextIndex(int i) {
            if (i + 1 == size) {
                i = 0;
            } else {
                i = i + 1;
            }
            return i;
        }

        @Override
        public String toString() {
            return "MyQueue{" +
                    "arr=" + Arrays.toString(arr) + "}";
        }
    }


}
