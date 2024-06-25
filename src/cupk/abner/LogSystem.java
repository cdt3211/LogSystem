package cupk.abner;

import java.util.ArrayList;
import java.util.List;

public class LogSystem {
    //单例模式，确保全局只有一个LogSystem实例
    private static LogSystem instance;
    private List<Logger> loggers = new ArrayList<>();

    private LogSystem(){}

    public static LogSystem getInstance(){
        if(instance == null){
            instance = new LogSystem();
        }
        return instance;
    }

    public void addLogger(Logger logger){
        loggers.add(logger);
    }

    //观察者模式，通知所有的Logger，记录日志
    public void log(LogLevel level, String message){
        LogMessage logMessage = new LogMessage(level, message);
        for(Logger logger : loggers){
            logger.log(logMessage);
        }
    }
}
