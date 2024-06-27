package cupk.abner;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

//日志配置类，用于读取配置文件
public class LogConfig {
    private Properties properties = new Properties();
    //读取配置文件
    public LogConfig(String configFilePath) {
        try (FileReader reader = new FileReader(configFilePath)) {
            properties.load(reader); //加载配置文件
        } catch (IOException e) {
            e.printStackTrace(); //打印异常
        }
    }

    public String getLogType() {
        return properties.getProperty("log.type"); //获取日志类型
    }

    public String getFilePath() {
        return properties.getProperty("log.filepath"); //获取日志文件路径
    }

    //获取配置文件中的属性
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
