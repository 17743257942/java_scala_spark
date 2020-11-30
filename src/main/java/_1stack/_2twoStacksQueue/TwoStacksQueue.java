package _1stack._2twoStacksQueue;

import java.util.Stack;

public class TwoStacksQueue {
    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    public TwoStacksQueue() {
        this.stackPush = new Stack<Integer>();
        this.stackPop = new Stack<Integer>();
    }

    public void add(int pushInt) {
        stackPush.add(pushInt);
    }

    public int poll() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("Queue is empty");
        } else if (stackPop.empty()) {
            while (!stackPush.empty()) {
                stackPop.add(stackPush.pop());
            }
        }
        return stackPop.pop();
    }

    public int peek() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("Queue is empty");
        } else if (stackPop.empty()) {
            while (!stackPush.empty()) {
                stackPop.add(stackPush.pop());
            }
        }
        return stackPop.peek();
    }


}
