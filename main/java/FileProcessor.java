import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    public static int countLetters(File file, char character) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        char c;
        int count = 0;
        while ((byte) (c = (char) reader.read()) != -1) {
            if (character == c) {
                count++;
            }
        }
        reader.close();
        return count;
    }

    /**
     * @return File-pointer offsets list for all text entries in file.
     */
    public static List<Long> findTextPositions(File file, String text) throws IOException {
        RandomAccessFile rafile = new RandomAccessFile(file, "r");
        List<Long> positions = new ArrayList<>();
        SequentialSampleMatcher stringComparator = new SequentialSampleMatcher(text);
        char c;
        while ((byte) (c = (char) rafile.read()) != -1) {
            if (stringComparator.check(c)) {
                positions.add(rafile.getFilePointer());
            }
        }
        return positions;
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
