package cupk.abner;

import java.util.Date;

//日志消息类
public class LogMessage {
    private LogLevel level;
    private String message;
    private Date date;
    //构造方法
    public LogMessage(LogLevel level, String message) {
        this.level = level;
        this.message = message;
        this.date = new Date();
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

}
