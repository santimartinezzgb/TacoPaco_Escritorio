package org.example.appescritoriotacopaco;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ViewPortada.fxml"));

        // Cargar la fuente (el número indica el tamaño por defecto al cargar)
        Font.loadFont(getClass().getResourceAsStream("/fonts/kat.ttf"), 14);

        Scene scene = new Scene(fxmlLoader.load(), 1820, 940);

        stage.setTitle("Taco Paco");
        stage.setScene(scene);
        stage.show();
    }
}
