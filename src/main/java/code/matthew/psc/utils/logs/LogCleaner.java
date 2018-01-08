package code.matthew.psc.utils.logs;

import com.google.common.io.Files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LogCleaner {

    public static void cleanOldLogs() {

        Logger.log(Logger.LogType.INFO, "Cleaning logs...");
        File logs = new File("logs");

        List<File> oldLogs = new ArrayList<>();

        if (!logs.exists()) {
            return;
        }

        for (File f : logs.listFiles()) {
            if (f != null) {
                String ftype = Files.getFileExtension(f.getName());
                if (ftype.equalsIgnoreCase("gz")) {
                    oldLogs.add(f);
                }
            }
        }

        for (File f : oldLogs) {
            if (!f.delete()) {
                Logger.log(Logger.LogType.ERROR, "ERROR DELETING LOG FILE");
            }
        }
        Logger.log(Logger.LogType.INFO, "Logs cleaned.");
    }
}
