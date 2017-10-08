package fileSearch;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    public static List<TextEntry> findTextEntries(File file, String text) throws IOException {
        List<TextEntry> entries = new ArrayList<>();

        if (file.isDirectory()) {
            for (File subfile : file.listFiles()) {
                entries.addAll(findTextEntries(subfile, text));
            }
            return entries;
        }
        // else ...
        RandomAccessFile rafile = new RandomAccessFile(file, "r");
        ChainMatcher stringComparator = new ChainMatcher(text);
        char c;
        while ((byte) (c = (char) rafile.read()) != -1) {
            if (stringComparator.check(c)) {
                TextEntry entry = new TextEntry(file, rafile.getFilePointer(), text.length());
                //entry.loadText();
                entries.add(entry);
                //System.out.println(entry);
            }
        }
        return entries;
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
