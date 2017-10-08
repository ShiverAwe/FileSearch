package fileSearch;

import fileSearch.UI.EntrySeekerBox;
import fileSearch.UI.EntryTextArea;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox();

        EntrySeekerBox seekerBox = new EntrySeekerBox();
        EntryTextArea entryTextArea = new EntryTextArea();
        ListView<TextEntry> list = new ListView<>();


        Button startButton = new Button("Start");
        startButton.setOnMouseClicked(e -> {
            EntrySeeker seeker = seekerBox.start();
            ObservableList<TextEntry> observableList = FXCollections.observableList(seeker.getEntries());
            list.setItems(observableList);
        });

//        list.setOnMouseClicked(e -> {
//            TextEntry entry = list.getSelectionModel().getSelectedItem();
//            entryTextArea.openEntry(entry);
//        });

        root.getChildren().addAll(seekerBox, startButton, entryTextArea, list);
        Scene scene = new Scene(root, 720, 450);
        primaryStage.setTitle("JavaFX TextArea");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
