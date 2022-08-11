package L3_链表_栈_队列;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用队列实现栈
 */
public class LinkedList_10 {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);

        System.out.println("myStack = " + myStack);
        System.out.println("myStack.pop() = " + myStack.pop());

        myStack.push(4);
        myStack.push(5);
        System.out.println("myStack = " + myStack);


        System.out.println("myStack.pop() = " + myStack.pop());
        System.out.println("myStack.pop() = " + myStack.pop());
        System.out.println("myStack.pop() = " + myStack.pop());
        System.out.println("myStack.pop() = " + myStack.pop());
        System.out.println("myStack.pop() = " + myStack.pop());
        System.out.println("myStack.pop() = " + myStack.pop());
    }

    public static class MyStack {
        private Queue<Integer> pushQueue = new LinkedList<>();
        private Queue<Integer> popQueue = new LinkedList<>();

        public void push(int value) {
            pushQueue.offer(value);
        }

        public Integer pop() {
            if (pushQueue.isEmpty() && popQueue.isEmpty()) {
                throw new RuntimeException("栈为空");
            }
            change();
            return popQueue.poll();
        }

        public void change() {
            while (pushQueue.size() > 1) {
                popQueue.offer(pushQueue.poll());
            }
            Queue<Integer> temp;
            temp = pushQueue;
            pushQueue = popQueue;
            popQueue = temp;
        }

        @Override
        public String toString() {
            return "MyStack{" +
                    "pushQueue=" + pushQueue +
                    ", popQueue=" + popQueue +
                    '}';
        }
    }


}
