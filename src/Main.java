import cupk.abner.*;

import java.util.Scanner;

public class Main {
    //确保日志系统只有一个实例
    private static LogSystem logSystem = LogSystem.getInstance();

    public static void main(String[] args) {
        LogConfig config = new LogConfig("config.properties");
        Logger logger = LoggerFactory.getLogger(config.getLogType(), config.getFilePath());
        logSystem.addLogger(logger);


        Scanner scanner = new Scanner(System.in);
        //在log中添加一行分隔符
        logSystem.log(LogLevel.INFO, "---------------------------------");
        logSystem.log(LogLevel.INFO, "启动计算器");

        while (true) {
            try {
                System.out.println("请输入‘+, -, *, /’ 或'退出'：");
                //获取输入的字符
                String operation = scanner.next();
                if (operation.equalsIgnoreCase("退出")) {
                    logSystem.log(LogLevel.INFO, "退出计算器");
                    break;
                }
                logSystem.log(LogLevel.DEBUG, "开始运算" + operation);

                System.out.println("请输入第一个你要计算的数:");
                double num1 = Double.parseDouble(scanner.next());
                logSystem.log(LogLevel.DEBUG, "输入的第一个数为: " + num1);

                System.out.println("请输入第二个你要计算的数:");
                double num2 = Double.parseDouble(scanner.next());
                logSystem.log(LogLevel.DEBUG, "输入的第二个数为: " + num2);

                double result = performOperation(operation, num1, num2);
                System.out.println("结果为: " + result);
                logSystem.log(LogLevel.INFO,   num1 + operation  + num2 + "=" + result);
            } catch (NumberFormatException e) {
                System.out.println("错误: 输入的不是数字！");
                logSystem.log(LogLevel.ERROR, "输入错误: " + e.getMessage());
            } catch (ArithmeticException e) {
                System.out.println("错误: " + e.getMessage());
                logSystem.log(LogLevel.ERROR, "算术问题: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("错误: " + e.getMessage());
                logSystem.log(LogLevel.ERROR, "参数错误: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("错误: " + e.getMessage());
                logSystem.log(LogLevel.ERROR, "未知错误: " + e.getMessage());
            }
        }
        scanner.close();
    }

    //四则运算
    private static double performOperation(String operation, double num1, double num2) {
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