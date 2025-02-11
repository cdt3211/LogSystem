package cupk.abner.LoggerConcrete;

import cupk.abner.LogMessage;
import cupk.abner.Logger;

import java.io.FileWriter;
import java.io.IOException;

//具体产品角色
public class XmlLogger implements Logger {
    private String filePath;

    public XmlLogger(String filePath) {
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
        return String.format("<log>\n\t<level>%s</level>\n\t<time>%s</time>\n\t<message>%s</message>\n\t<performance>%s</performance>\n\t<caller>%s</caller>\n</log>", message.getLevel(),message.getTime(),message.getMessage(),message.getPerformance(), message.getCaller());
    }
}
