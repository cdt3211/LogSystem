package cupk.abner.DecoratorConcerte;

import cupk.abner.*;

public class PerformanceLoggerDecorator extends LoggerDecorator {
    public PerformanceLoggerDecorator(Logger decoratorLogger) {
        super(decoratorLogger);
    }

    @Override
    public void log(LogMessage message) {
        String performanceInfo = getPerformanceInfo();
        message.setPerformance(performanceInfo);
        super.log(message);
    }

    private String getPerformanceInfo() {
        Runtime runtime =Runtime.getRuntime();
        long freeMemory = runtime.freeMemory();
        long totalMemory = runtime.totalMemory();
        long usedMemory = totalMemory - freeMemory;
        return "Memory Used:" + usedMemory ;
    }
}
