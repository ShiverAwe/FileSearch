package fileSearch;

import javax.swing.*;
import java.io.*;

public class FileUtils {

    public static File openDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int ret = fileChooser.showDialog(null, "Выберите файл");
        if (ret == JFileChooser.APPROVE_OPTION) return fileChooser.getSelectedFile();
        return null;
    }


    public static void printFile(File file) throws IOException {
        if (!file.isFile()) throw new FileNotFoundException("Not a file!");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }

}
