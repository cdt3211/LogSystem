package cupk.abner;

import cupk.abner.DecoratorConcerte.CallerInfoLoggerDecorator;
import cupk.abner.DecoratorConcerte.PerformanceLoggerDecorator;
import cupk.abner.DecoratorConcerte.TimestampLoggerDecorator;
import cupk.abner.LoggerConcrete.JsonLogger;
import cupk.abner.LoggerConcrete.TextLogger;
import cupk.abner.LoggerConcrete.XmlLogger;

//工厂模式，充当工厂角色，用于创建具体的日志记录器
//策略模式，充当上下文角色，用于选择具体的日志记录器
//装饰器模式，充当装饰器角色，用于装饰日志记录器
public class LoggerFactory {
    public static Logger getLogger(String type, String filePath) {
        switch (type.toLowerCase()) {
            case "text":
                return new TextLogger(filePath);
            case "xml":
                return new XmlLogger(filePath);
            case "json":
                return new JsonLogger(filePath);
            default:
                throw new IllegalArgumentException("错误的日志类型：" + type);
        }
    }
    public static Logger gerDecoratorLogger(Logger logger,boolean addTimestamp,boolean addCallerInfo,boolean addPerformance){
        if (addTimestamp) {
            logger = new TimestampLoggerDecorator(logger);
        }
        if (addCallerInfo) {
            logger = new CallerInfoLoggerDecorator(logger);
        }
        if (addPerformance) {
            logger = new PerformanceLoggerDecorator(logger);
        }
        return logger;
    }
}

