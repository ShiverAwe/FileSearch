import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        //System.out.println(FileManager.countFiles(new File("F:\\JavaProjects")));
//
//        final int Q = 1000007;
//        int hash1 = 0, hash2 = 0;
//        int[] seq1 = {1, 314, 1};
//        int [] seq2 = {5,46,88,1,314,1};
//        for (int i = 0; i < 10; i++) {
//            hash1 = (hash1 - (seq1[i] << 3 % Q)) % Q;
//            hash1 = (hash1 << 1 + ch) % Q;
//        }



        File file = FileManager.openDialog();
        //System.out.println(FileProcessor.countLetters(file, 'a'));
        List<Long> positions = FileProcessor.findTextPositions(file, "public");
        System.out.println(positions.size());
    }
}
