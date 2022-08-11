package L3_链表_栈_队列;

import java.util.Arrays;

/**
 * 数组实现栈
 */
public class LinkedList_07 {
    public static void main(String[] args) {
        MyStack myStack = new MyStack(5);
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);

        System.out.println("myStack = " + myStack);


        System.out.println("myStack.pop() = " + myStack.pop());
        System.out.println("myStack.pop() = " + myStack.pop());
        System.out.println("myStack.pop() = " + myStack.pop());
        System.out.println("myStack.pop() = " + myStack.pop());
        System.out.println("myStack.pop() = " + myStack.pop());

        myStack.push(6);
        myStack.push(7);
        myStack.push(8);

        System.out.println("myStack = " + myStack);

        System.out.println("myStack.pop() = " + myStack.pop());
        System.out.println("myStack.pop() = " + myStack.pop());
        System.out.println("myStack.pop() = " + myStack.pop());
    }

    public static class MyStack {
        private final int[] arr;
        private final int size;
        private int top;

        public MyStack(int size) {
            this.arr = new int[size];
            this.top = 0;
            this.size = size;
        }

        public void push(int value) {
            if (top == size) {
                throw new RuntimeException("栈已满");
            }
            arr[top] = value;
            top++;
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("栈为空");
            }
            top--;
            return arr[top];
        }
    }
}
