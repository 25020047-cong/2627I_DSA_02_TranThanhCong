import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class w3_tailop_25020047 {

    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    static int precedence(char op) {
        if (op == '*' || op == '/') return 2;
        if (op == '+' || op == '-') return 1;
        return 0;
    }

    static String infixToPostfix(String expr) {
        StringBuilder output = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();

        int i = 0;
        while (i < expr.length()) {
            char c = expr.charAt(i);

            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            if (Character.isLetterOrDigit(c) || c == '.') {
                int j = i;
                while (j < expr.length()
                        && (Character.isLetterOrDigit(expr.charAt(j)) || expr.charAt(j) == '.')) {
                    j++;
                }
                output.append(expr, i, j).append(' ');
                i = j;
                continue;
            }

            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop()).append(' ');
                }
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Thieu dau ngoac mo '('");
                }
                stack.pop();
            } else if (isOperator(c)) {
                while (!stack.isEmpty() && stack.peek() != '('
                        && precedence(stack.peek()) >= precedence(c)) {
                    output.append(stack.pop()).append(' ');
                }
                stack.push(c);
            } else {
                throw new IllegalArgumentException("Ky tu khong hop le: '" + c + "'");
            }
            i++;
        }

        while (!stack.isEmpty()) {
            char top = stack.pop();
            if (top == '(') {
                throw new IllegalArgumentException("Thieu dau ngoac dong ')'");
            }
            output.append(top).append(' ');
        }

        return output.toString().trim();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap bieu thuc trung to: ");
        String expr = sc.nextLine();

        try {
            System.out.println("Bieu thuc hau to: " + infixToPostfix(expr));
        } catch (IllegalArgumentException e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }
}