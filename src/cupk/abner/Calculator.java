package cupk.abner;

public class Calculator {
    //四则运算
    public static double performOperation(String operation, double num1, double num2) {
        switch (operation) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("除数不能为 0！");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("输入了错误的运算符！");
        }
    }
}
