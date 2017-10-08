package fileSearch.UI;

import fileSearch.TextEntry;
import javafx.scene.control.TextArea;

public class EntryTextArea extends TextArea {

    public void openEntry(TextEntry entry){
        entry.loadPreviewText();
        setText(entry.getCashedText());
    }

}
