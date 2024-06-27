package cupk.abner;

//装饰器模式，用于添加额外日志信息
public abstract class LoggerDecorator implements Logger {
    protected Logger decoratorLogger;

    public LoggerDecorator(Logger decoratorLogger) {
        this.decoratorLogger = decoratorLogger;
    }

    @Override
    public void log(LogMessage message) {
        decoratorLogger.log(message);
    }
}
