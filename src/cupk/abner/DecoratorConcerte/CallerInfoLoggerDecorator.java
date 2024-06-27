package cupk.abner.DecoratorConcerte;

import cupk.abner.LogMessage;
import cupk.abner.Logger;
import cupk.abner.LoggerDecorator;

//
public class CallerInfoLoggerDecorator extends LoggerDecorator {
    public CallerInfoLoggerDecorator(Logger decoratorLogger) {
        super(decoratorLogger);
    }

    @Override
    public void log(LogMessage message){
        message.setCaller(getCallerInfo());
        super.log(message);
    }

    private String getCallerInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stackTrace.length; i++) {
            StackTraceElement element = stackTrace[i];
            // 过滤掉装饰器自身的调用信息
            if (!element.getClassName().equals(CallerInfoLoggerDecorator.class.getName())
                    && !element.getClassName().equals(PerformanceLoggerDecorator.class.getName())
                    && !element.getClassName().equals(TimestampLoggerDecorator.class.getName())
                    && !element.getClassName().equals(LoggerDecorator.class.getName())
                    && !element.getClassName().startsWith("cupk.abner")
                    && !element.getClassName().startsWith("java.lang.Thread")) {
                return element.getClassName() + "." + element.getMethodName() + "(): " + element.getLineNumber();
            }
        }
        return "Unknown caller";
    }
}

