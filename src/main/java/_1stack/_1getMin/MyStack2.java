package _1stack._1getMin;

import java.util.Stack;

/**
 * 压入费空间，弹出省时间
 */
public class MyStack2 {

    private Stack<Integer> stackData;  //正常的栈
    private Stack<Integer> stackMin;   //存每次产生的最小值

    public MyStack2() {
        this.stackData = new Stack<Integer>();
        this.stackMin = new Stack<Integer>();

    }

    public void push(int newNum) {
        if (this.stackMin.isEmpty()) {
            this.stackData.push(newNum);
        } else if (newNum <= this.getMin()) {
            this.stackMin.push(newNum);
        } else {
            int newMin = this.stackMin.peek();
            this.stackMin.push(newMin);
        }
        this.stackData.push(newNum);
    }

    public int pop() {
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("your _1stack is empty");
        }
        this.stackMin.pop();
        return this.stackData.pop();
    }

    public int getMin() {
        if (this.stackMin.isEmpty()) {
            throw new RuntimeException("your _1stack is empty");
        }
        return this.stackMin.peek();

    }


}
