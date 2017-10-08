package fileSearch;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileManager {


    /**
     * @param directory - path
     * @return list of subitems in given directory
     * @throws NullPointerException if given path is not a directory
     */
    public static List<File> getContentList(File directory) {
        List<File> content = Arrays.asList(directory.listFiles());
        return content;
    }

    public static int countFiles(File directory) {
        int count = 0;
        for (File unit : directory.listFiles()) {
            if (unit.isDirectory()) count += countFiles(unit);
            if (unit.isFile()) {
                count++;
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
