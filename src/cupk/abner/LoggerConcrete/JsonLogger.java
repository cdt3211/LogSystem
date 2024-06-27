package cupk.abner.LoggerConcrete;

import cupk.abner.LogMessage;
import cupk.abner.Logger;
import java.io.FileWriter;
import java.io.IOException;

public class JsonLogger implements Logger {
    private String filePath;

    public JsonLogger(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void log(LogMessage message) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(formatMessage(message) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatMessage(LogMessage message) {
        return String.format("{'level':'%s','time':'%s','message':'%s','performance':'%s','caller':'%s'}",message.getLevel(),message.getTime(),message.getMessage(),message.getPerformance(),message.getCaller());
    }
}
