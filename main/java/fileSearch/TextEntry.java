package fileSearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * fileSearch.TextEntry class contains file and position where entry was found.
 */
public class TextEntry {
    /**
     * The file where entry is found
     */
    private final File file;

    /**
     * Offset from file start in bytes where to find an entry text
     */
    private final long position;

    /**
     * Length of entry text in bytes
     */
    private final long lenght;

    private String cashedText;

    /**
     * Loads text around entry to be shown in preview and cashes it.
     *
     * @return Text of entry and around.
     * @throws IOException if file path is incorrect or directory found
     */
    public String loadPreviewText(){
        final int DEFAULT_RANGE = 10;
        return loadPreviewText(DEFAULT_RANGE);
    }

    public String loadPreviewText(int range) {
        long startPosition = position - range;
        if (startPosition < 0) startPosition = 0;
        long endPosition = position + lenght + range;
        if (endPosition >= file.length()) endPosition = file.length() - 1;
        long length = endPosition - startPosition;

        RandomAccessFile rafile;
        StringBuilder text = new StringBuilder();
        try {
            rafile = new RandomAccessFile(file, "r");
            rafile.seek(startPosition);
            for (int i = 0; i < length; i++) {
                text.append((char) rafile.read());
            }
            cashedText = text.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text.toString();

    }


    public TextEntry(File file, long position, long lenght) {
        this.file = file;
        this.position = position;
        this.lenght = lenght;
    }

    public File getFile() {
        return file;
    }

    public long getPosition() {
        return position;
    }

    public long getLenght() {
        return lenght;
    }

    public String getCashedText() {
        return cashedText;
    }

    @Override
    public String toString() {
        return "fileSearch.TextEntry{" +
                "file=" + file +
                ", position=" + position +
                (cashedText != null ? ", cashedText='" + cashedText + '\'' : "") +
                '}';
    }
}
