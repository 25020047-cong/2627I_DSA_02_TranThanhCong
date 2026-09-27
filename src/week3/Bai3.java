package week3;

import java.util.Scanner;
import java.util.Stack;

public class Bai3 {

    private Stack<Integer> stackIn;
    private Stack<Integer> stackOut;

    public Bai3() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    public void enqueue(int value) {
        stackIn.push(value);
    }

    public int dequeue() {
        transferIfNeeded();
        if (stackOut.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return stackOut.pop();
    }

    public int print() {
        transferIfNeeded();
        if (stackOut.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return stackOut.peek();
    }

    private void transferIfNeeded() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
    }

    public boolean isEmpty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bai3 queue = new Bai3();

        int q = Integer.parseInt(sc.nextLine().trim());

        for (int i = 0; i < q; i++) {
            String line = sc.nextLine().trim();
            String[] parts = line.split("\\s+");
            int type = Integer.parseInt(parts[0]);

            switch (type) {
                case 1:
                    int value = Integer.parseInt(parts[1]);
                    queue.enqueue(value);
                    break;
                case 2:
                    queue.dequeue();
                    break;
                case 3:
                    System.out.println(queue.print());
                    break;
            }
        }

        sc.close();
    }
}