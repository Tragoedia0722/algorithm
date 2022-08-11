package L3_链表_栈_队列;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在基本功能的基础上（push、pop），实现返回栈中最小值（getMin）的功能
 */
public class LinkedList_08 {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(5);
        myStack.push(4);
        myStack.push(3);
        myStack.push(2);
        myStack.push(1);

        System.out.println("myStack = " + myStack);

        myStack.pop();
        myStack.pop();
        myStack.pop();

        System.out.println("myStack = " + myStack);

        myStack.push(2);
        myStack.push(9);
        myStack.push(10);

        System.out.println("myStack = " + myStack);

    }

    public static class MyStack {
        private final Stack<Integer> stack;
        private final Stack<Integer> min;

        public MyStack() {
            stack = new Stack<>();
            min = new Stack<>();
        }

        public void push(int value) {
            stack.push(value);
            if (min.isEmpty()) {
                min.push(value);
            } else {
                min.push(Math.min(value, getMin()));
            }
        }

        public int pop() {
            min.pop();
            return stack.pop();
        }

        public void peek() {
            stack.peek();
        }

        public int getMin() {
            return min.peek();
        }

        @Override
        public String toString() {
            return "MyStack{" +
                    "stack=" + stack +
                    ", min=" + min +
                    '}';
        }
    }

}
