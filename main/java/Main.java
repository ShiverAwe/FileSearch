import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = FileManager.openDialog();
        List<Long> positions = FileProcessor.findTextPositions(file, "public");
        System.out.println(positions.size());
    }
}
