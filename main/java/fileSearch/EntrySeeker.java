package fileSearch;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * EntrySeeker allows to smoothly look for text entries in another thread and add to entrylist without main thread stops.
 * Immutable.
 */
public class EntrySeeker {

    private final File file;

    private final String sampleText;

    private final List<TextEntry> entries = new LinkedList<>();

    private boolean EOF = false;

    public boolean EOF() {
        return EOF;
    }

    public List<TextEntry> getEntries() {
        return entries;
    }

    private boolean started = false;

    public void start() {
        if (!started) {
            new Thread(() -> addEntriesFromFile(file)).start();
            started = true;
        }
    }

    private void addEntriesFromFile(File file) {
        try {
            System.out.println(file);
            if (file.isDirectory()) {
                for (File subfile : file.listFiles()) {
                    addEntriesFromFile(subfile);
                }
            }
            // if this is really file ...
            RandomAccessFile rafile = new RandomAccessFile(file, "r");
            ChainMatcher matcher = new ChainMatcher(sampleText);
            char c;
            while ((byte) (c = (char) rafile.read()) != -1) {
                if (matcher.check(c)) {
                    TextEntry entry = new TextEntry(file, rafile.getFilePointer(), sampleText.length());
                    entries.add(entry);
                }
            }
            this.EOF = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EntrySeeker(File file, String sampleText){
        this.file = file;
        this.sampleText = sampleText;
        start();
    }
}
