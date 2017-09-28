import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello again, Shefer");
        System.out.println(Integer.MAX_VALUE + "  " + (Integer.MAX_VALUE + Integer.MAX_VALUE) );
/*
        List<File> fileList = FileManager.getContentList(new File("C:/"));
        for (File file : fileList) {
            System.out.println(file.getName());
        }
*/

        System.out.println(FileManager.countFiles(new File("F:/")));
    }
}
