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

    public void cargarMesas() {
        Call<List<Mesa>> call = RetrofitClient.getApi().getMesas();

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Mesa>> call, Response<List<Mesa>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Mesa> mesas = response.body();
                    for (Mesa m : mesas) {
                        System.out.println("Mesa: " + m.getNombre() + " y Ocupada: " + m.isOcupada());
                    }
                    Platform.runLater(() -> {
                        for (Mesa mesa : mesas) {
                            HBox box = mapaMesas.get(mesa.getNombre());
                            if (box != null) {
                                box.getChildren().clear();

                                Label nombreMesa = new Label(mesa.getNombre());
                                nombreMesa.setTextFill(Color.WHITE);
                                nombreMesa.setPrefWidth(400);
                                nombreMesa.setStyle("-fx-padding: 0 0 0 50;");

                                if (mesa.isOcupada()) {
                                    // Ocupada → rojo + botón Liberar
                                    box.setStyle("-fx-background-color: #ff4d4d; -fx-padding: 10; -fx-spacing: 220;");

                                    Button btn_liberar = new Button("Liberar mesa");
                                    btn_liberar.setStyle("-fx-background-color: blue; -fx-text-fill: #ffffff; " +
                                            "-fx-background-radius: 10px; -fx-border-radius;" +
                                            " -fx-padding: 5 15 5 15; -fx-font-size: 30px;");
                                    btn_liberar.setOnAction(e -> liberarMesa(mesa));

                                    box.getChildren().addAll(nombreMesa, btn_liberar);
                                } else {
                                    // Libre → verde
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
