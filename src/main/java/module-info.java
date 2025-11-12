module org.example.appescritoriotacopaco {
    requires javafx.controls;
    requires javafx.fxml;
    requires retrofit2;


    opens org.example.appescritoriotacopaco to javafx.fxml;
    exports org.example.appescritoriotacopaco;
    exports servicios;
    opens servicios to javafx.fxml;
}