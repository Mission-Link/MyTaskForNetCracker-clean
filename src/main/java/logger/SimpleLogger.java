package logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SimpleLogger {
    private static final String FS = File.separator;

    private String PATH = "." + FS + "src" + FS + "test" + FS + "logs";
    private String uniqueName;

    public SimpleLogger() {
        this.uniqueName = generateUniqueDT();
    }

    public void writeLog(ArrayList<String> arrayList) {
        File dirForLogs = createDirForLogs();
        if (dirForLogs == null) {
            return;
        }
        File logFile = new File(PATH + FS + uniqueName + ".log");
        try {
            logFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Impossible to create file for logs");
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(logFile, true);

            if (arrayList.size() != 0) {
                for (String tmp : arrayList) {
                    fw.write(tmp + System.lineSeparator());
                }
            }

        } catch (Exception e) {
            System.out.println("Log file doesn't exist");
            System.out.println("Impossible to write log");
        }finally {
            try {
                fw.flush();
                fw.close();
            } catch (IOException e) {
                System.out.println("Impossible to close fileWriter object");
            }
        }

    }//end of method writeLog()

    private File createDirForLogs() {
        File file = new File(PATH);
        if (!file.exists()) {
            System.out.println("Directory for logs does not exist, creating...");
            file.mkdir();
            if (!file.exists()) {
                System.out.println("Impossible to create directory for logs");
                return null;
            } else {
                System.out.println("Created successfully");
            }
        }
        return file;
    }//end of method create dirForLogs()


    private static String generateUniqueDT() {
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HHmmss");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("ddMMYY");
        String f1 = formatter1.format(localTime);
        String f2 = formatter2.format(localDate);
        return f2 + "_" + f1;
    }

}//end of class
