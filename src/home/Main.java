package home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        primaryStage.setTitle("HomonicCipher");
        primaryStage.setScene(new Scene(root, 770, 680));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
