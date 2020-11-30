package _1stack._5sortStack;

import java.util.Stack;

public class SortStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.add(3);
        stack.add(1);
        stack.add(4);
        stack.add(4);
        sortStackByStack(stack);
    }

    public static void sortStackByStack(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<Integer>();
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            while (!help.isEmpty() && help.peek() < cur) {
                stack.push(help.pop());
            }
            help.push(cur);
        }
        while (!help.isEmpty()) {
            stack.push(help.pop());
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

}
