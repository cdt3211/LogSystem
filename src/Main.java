import cupk.abner.*;

import java.util.Scanner;

import static cupk.abner.Calculator.performOperation;

public class Main {
    //确保日志系统只有一个实例
    private static LogSystem logSystem = LogSystem.getInstance();

    public static void main(String[] args) {
        LogConfig config = new LogConfig("config.properties");

        //创建日志对象
        Logger logger = LoggerFactory.getLogger(config.getLogType(), config.getFilePath());
        //根据配置添加装饰器
        boolean addPerformance = Boolean.parseBoolean(config.getProperty("log.addPerformance", "false"));
        boolean addTimestamp = Boolean.parseBoolean(config.getProperty("log.addTimestamp", "false"));
        boolean addCallerInfo = Boolean.parseBoolean(config.getProperty("log.addCallerInfo", "false"));
        //添加装饰器
        logger = LoggerFactory.gerDecoratorLogger(logger, addTimestamp, addCallerInfo,addPerformance);
        logSystem.addLogger(logger);

        //创建xml日志对象
        Logger xmlLogger = LoggerFactory.getLogger("xml", "log.xml");
        logSystem.addLogger(xmlLogger);

        Logger jsonLogger = LoggerFactory.getLogger("json", "log.json");
        logSystem.addLogger(jsonLogger);

        //开始输入
        Scanner scanner = new Scanner(System.in);
        //分隔符
        logSystem.log(LogLevel.INFO, "------------启动计算器----------");

        while (true) {
            try {
                System.out.println("请输入‘+, -, *, /’ 或'tc'(退出)：");
                //获取输入的字符
                String operation = scanner.next();
                if (operation.equalsIgnoreCase("tc")) {
                    logSystem.log(LogLevel.INFO, "----------退出计算器----------");
                    break;
                } else if (!operation.equals("+") && !operation.equals("-") && !operation.equals("*") && !operation.equals("/")) {
                        throw new IllegalArgumentException("输入了错误的运算符！");
                }
                logSystem.log(LogLevel.DEBUG, "开始运算 '" + operation + "'");

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
}