import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileManager {

    public static List<File> getContentList(File directory) {
        List<File> content;
        File[] listFiles = directory.listFiles();
        if (listFiles != null) {
            content = Arrays.asList(listFiles);
        } else {
            content = Collections.emptyList();
        }
        return content;
    }

    public static int countFiles(File directory) {
        int count = 0;

        for (File unit : directory.listFiles()) {
            if (unit.isDirectory()) count += countFiles(unit);
            if (unit.isFile()) {
                count += 1;
            }
        }
        return count;
    }

    public static File openDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int ret = fileChooser.showDialog(null, "Выберите файл");
        if (ret == JFileChooser.APPROVE_OPTION) return fileChooser.getSelectedFile();
        return null;
    }

}
