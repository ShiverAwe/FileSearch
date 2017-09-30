import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //System.out.println(FileManager.countFiles(new File("F:\\JavaProjects")));

        File file = FileManager.openDialog();
        System.out.println(FileProcessor.countLetters(file, 'a'));
    }
}
