package fileSearch.UI;

import fileSearch.FileUtils;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileTextArea extends TextArea {

    File file;

    {
        setWrapText(true);
    }

    public void openFileWithDialog() {
        this.file = FileUtils.openDialog();
        openFile(file);
    }

    public void openFile(File file) {
        this.file = file;
        RandomAccessFile rafile = null;
        try {
            rafile = new RandomAccessFile(file, "r");
            char c;
            while ((byte) (c = (char) rafile.read()) != -1) {
                this.appendText(c + "");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
