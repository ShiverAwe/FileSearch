import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * TextEntry class contains file and position where entry was found.
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


    public TextEntry(File file, long position, long lenght) {
        this.file = file;
        this.position = position;
        this.lenght = lenght;
    }

    /**
     * Loads text around entry to be shown in preview and cashes it.
     *
     * @return Text of entry and around.
     * @throws IOException if file path is incorrect or directory found
     */
    public String loadPreviewText() throws IOException {
        long startPosition = position - 20;
        if (startPosition < 0) startPosition = 0;
        long endPosition = position + lenght + 20;
        if (endPosition >= file.length()) endPosition = file.length() - 1;
        long length = endPosition - startPosition;

        RandomAccessFile rafile = new RandomAccessFile(file, "r");

        String text = "";
        rafile.seek(startPosition);
        for (int i = 0; i < length; i++) {
            text += (char) rafile.read(); // TODO optimize (maybe);
        }
        cashedText = text;

        return text;
    }

    @Override
    public String toString() {
        return "TextEntry{" +
                "file=" + file +
                ", position=" + position +
                (cashedText != null ? ", cashedText='" + cashedText + '\'' : "") +
                '}';
    }
}
