package cupk.abner.DecoratorConcerte;

import cupk.abner.LogMessage;
import cupk.abner.Logger;
import cupk.abner.LoggerDecorator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampLoggerDecorator extends LoggerDecorator {
    public TimestampLoggerDecorator(Logger decoratorLogger) {
        super(decoratorLogger);
    }

    @Override
    public void log(LogMessage message) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        message.setTime(timestamp);
        super.log(message);
    }
}
