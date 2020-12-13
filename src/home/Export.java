package home;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

public class Export {
    private String path = "";
    byte[] data;
    HashMap<Character, ArrayList<Integer>> keyMap = new HashMap<>();
    protected Export() {
    }

    protected void exportText(Button button) {

        final FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) button.getScene().getWindow();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try {
                Files.write(file.toPath(), this.data);
            } catch (IOException ex) {

            }
        }
    }

    private void stringToJson(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(this.keyMap);
            this.data = json.getBytes();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    protected void exportMap(Button button) {
        final FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) button.getScene().getWindow();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Json doc", "*.json"));
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            this.path = file.getAbsolutePath();
            stringToJson();
            try {
                Files.write(file.toPath(), this.data);
            } catch (IOException ex) {

            }
        }
    }

    protected String getPath() {
        return this.path;
    }

    protected  void setMapToExport( HashMap<Character, ArrayList<Integer>> keyMap){ this.keyMap = keyMap;}

    protected void setStringToExport(String text) {
        this.data = text.getBytes();
    }
}
