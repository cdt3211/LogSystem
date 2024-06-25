package cupk.abner.LoggerConcrete;

import cupk.abner.LogMessage;
import cupk.abner.Logger;

import java.io.FileWriter;
import java.io.IOException;

//具体产品角色
public class TextLogger implements Logger {
    private String filePath;

    public TextLogger(String filePath) {
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
        return String.format("日志等级：%s,信息：%s,时间：%s", message.getLevel(), message.getMessage(), message.getDate());
    }
}
