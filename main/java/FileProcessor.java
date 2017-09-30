import java.io.*;

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
