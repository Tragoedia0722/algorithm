package L3_链表_栈_队列;

import java.util.Stack;

/**
 * 使用栈实现队列
 */
public class LinkedList_09 {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.offer(1);
        myQueue.offer(2);
        myQueue.offer(3);

        System.out.println("myQueue = " + myQueue);

        System.out.println("myQueue.poll() = " + myQueue.poll());
        System.out.println("myQueue.poll() = " + myQueue.poll());
        System.out.println("myQueue.poll() = " + myQueue.poll());
    }

    /**
     * 使用两个栈实现队列
     * 入队：
     */
    public static class MyQueue {
        private final Stack<Integer> offerStack;
        private final Stack<Integer> pollStack;

        public MyQueue() {
            offerStack = new Stack<>();
            pollStack = new Stack<>();
        }

        public void offer(int value) {
            offerStack.push(value);
        }

        public int poll() {
            change();
            return pollStack.pop();
        }

        public void change() {
            if (pollStack.isEmpty()) {
                while (!offerStack.isEmpty()) {
                    pollStack.push(offerStack.pop());
                }
            }
        }

        @Override
        public String toString() {
            return "MyQueue{" +
                    "offerStack=" + offerStack +
                    ", pollStack=" + pollStack +
                    '}';
        }
    }
}
