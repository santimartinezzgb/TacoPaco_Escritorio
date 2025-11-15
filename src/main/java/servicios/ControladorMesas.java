package servicios;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import org.example.appescritoriotacopaco.Mesa;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.awt.Color.white;

public class ControladorMesas {

    @FXML private HBox mesa1;
    @FXML private HBox mesa2;
    @FXML private HBox mesa3;
    @FXML private HBox mesa4;
    @FXML private HBox mesa5;
    @FXML public Button actualizar;

    @FXML
    private void actualizarMesas() {
        cargarMesas();
    }

    // Relacionar nombre de mesa con HBox del FXML
    private final Map<String, HBox> mapaMesas = new HashMap<>();

    @FXML
    public void initialize() {
        mapaMesas.put("Mesa1", mesa1);
        mapaMesas.put("Mesa2", mesa2);
        mapaMesas.put("Mesa3", mesa3);
        mapaMesas.put("Mesa4", mesa4);
        mapaMesas.put("Mesa5", mesa5);

        cargarMesas();
    }

    // Cargar mesas desde el servidor y actualizar la interfaz
    public void cargarMesas() {

        // Obtener mesas desde el servidor
        Call<List<Mesa>> call = RetrofitClient.getApi().getMesas();

        // LLamada a la API de forma asíncrona
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Mesa>> call, Response<List<Mesa>> response) {

                // Si la respuesta es correcta, actualizar la interfaz
                if (response.isSuccessful() && response.body() != null) {

                    // Obtener lista de mesas
                    List<Mesa> mesas = response.body();

                    // Actualizar interfaz en el hilo de la aplicación JavaFX
                    Platform.runLater(() -> {
                        for (Mesa mesa : mesas) {

                            // Obtener el HBox correspondiente a la mesa
                            HBox box = mapaMesas.get(mesa.getNombre());
                            if (box != null) {

                                // Limpiar contenido previo
                                box.getChildren().clear();

                                // Crear etiqueta con el nombre de la mesa
                                Label nombreMesa = new Label(mesa.getNombre());

                                // Estilo de la etiqueta
                                nombreMesa.setTextFill(Color.WHITE);
                                nombreMesa.setPrefWidth(400);
                                nombreMesa.setStyle("-fx-padding: 0 0 0 50;");

                                if (mesa.isOcupada()) { // Mesa ocupada
                                    //  → rojo
                                    if(mesa.a_pagar){ // Si la mesa está a pagar
                                        box.setStyle("-fx-background-color: #ff4d4d; -fx-padding: 10; -fx-spacing: 220;");
                                        // Añadir nombre de la mesa
                                        box.getChildren().addAll(nombreMesa);
                                        // Añadir botón para liberar mesa
                                        Button liberarButton = new Button("Limpiar mesa");
                                        liberarButton.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;" +
                                                "-fx-font-weight: bold; -fx-padding: 5 15 5 15; -fx-border-radius: 5;" +
                                                " -fx-background-radius: 5; -fx-cursor: hand; -fx-font-size: 25;" +
                                                "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.7) , 8, 0.5 , 5 , 5 );" +
                                                "");
                                        liberarButton.setOnAction(e -> liberarMesa(mesa));
                                        box.getChildren().add(liberarButton);
                                    } else{
                                        box.setStyle("-fx-background-color: #ff4d4d; -fx-padding: 10;");
                                        box.getChildren().add(nombreMesa);
                                    }


                                } else { // Mesa libre
                                    //  → verde
                                    box.setStyle("-fx-background-color: #1C8477; -fx-padding: 10;");
                                    box.getChildren().add(nombreMesa);
                                }
                            }
                        }
                    });

                } else {
                    System.out.println("Error al obtener mesas: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Mesa>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void liberarMesa(Mesa mesa) {

        // Liberar mesa
        Mesa mesaLibre = new Mesa(mesa.getNombre(), false);
        RetrofitClient.getApi().ocuparMesa(mesa.getNombre(), mesaLibre)
            .enqueue(new Callback<Mesa>() {
                @Override
                public void onResponse(Call<Mesa> call, Response<Mesa> response) {
                    if (response.isSuccessful()) {
                        System.out.println("Mesa liberada: " + mesa.getNombre());
                        cargarMesas();
                    }
                }

                @Override
                public void onFailure(Call<Mesa> call, Throwable t) {
                    t.printStackTrace();
                }
            });

    }
}
