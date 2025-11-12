package org.example.appescritoriotacopaco;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ViewPortada.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1820, 940);
        stage.setTitle("Taco Paco");
        stage.setScene(scene);
        stage.show();
    }
}
