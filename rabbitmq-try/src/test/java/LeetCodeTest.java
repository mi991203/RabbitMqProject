import java.util.Stack;

public class LeetCodeTest {

    public static void main(String[] args) {
        final MinStack stack = new MinStack();
        stack.push(512);
        stack.push(-1024);
        stack.push(-1024);
        stack.push(512);
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
    }


    static class MinStack {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            if (minStack.size() == 0 || minStack.peek() >= val) {
                minStack.push(val);
            }
            stack.push(val);

        }

        public void pop() {
            if (stack.peek().intValue() == minStack.peek().intValue()) {
                minStack.pop();
            }
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}
