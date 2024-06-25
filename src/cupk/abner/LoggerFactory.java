package cupk.abner;

import cupk.abner.LoggerConcrete.TextLogger;
import cupk.abner.LoggerConcrete.XmlLogger;

//工厂模式，充当工厂角色，用于创建具体的日志记录器
public class LoggerFactory {
    public static Logger getLogger(String type, String filePath) {
        switch (type.toLowerCase()) {
            case "text":
                return new TextLogger(filePath);
            case "xml":
                return new XmlLogger(filePath);
            default:
                throw new IllegalArgumentException("错误的日志类型：" + type);
        }
    }
}

