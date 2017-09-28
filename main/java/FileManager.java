import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileManager {

    public static List<File> getContentList(File directory){
        List<File> content;
        File[] listFiles = directory.listFiles();
        if (listFiles != null) {
            content = Arrays.asList(listFiles);
        } else {
            content = Collections.emptyList();
        }
        return content;
    }

    public static int countFiles(File directory){
        int count = 0;

        for (File unit : getContentList(directory)) {
            if (unit.isDirectory()) count += countFiles(unit);
            if (unit.isFile()) count+=1;
        }
        return count;
    }
}
