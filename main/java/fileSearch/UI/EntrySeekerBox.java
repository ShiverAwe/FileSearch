package fileSearch.UI;

import fileSearch.EntrySeeker;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.File;

import static fileSearch.FileManager.openDialog;

public class EntrySeekerBox extends VBox{

    private File file;

    private EntrySeeker seeker;

    public EntrySeeker getSeeker() {
        return seeker;
    }

    private TextField sampleTextField = new TextField();

    private Label fileNameField = new Label();

    private Button openFileButton = new Button("Select file");

    {
        openFileButton.setOnMouseClicked(e -> {
            file = openDialog();
            fileNameField.setText(file.getName());
        });
    }

    public EntrySeeker start () {
            seeker = new EntrySeeker(file, sampleTextField.getText());
            seeker.start();
            return seeker;
    }

    {
        getChildren().addAll(sampleTextField, fileNameField, openFileButton);
    }

}
