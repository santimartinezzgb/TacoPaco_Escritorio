package servicios;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.input.MouseEvent;
import org.example.appescritoriotacopaco.Mesa;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorMesas {

    @FXML private HBox mesa1;
    @FXML private HBox mesa2;
    @FXML private HBox mesa3;
    @FXML private HBox mesa4;
    @FXML private HBox mesa5;

    private final Map<String, HBox> mesaMap = new HashMap<>();

    @FXML
    public void initialize() {
        // Mapear nombres a HBox
        mesaMap.put("Mesa 1", mesa1);
        mesaMap.put("Mesa 2", mesa2);
        mesaMap.put("Mesa 3", mesa3);
        mesaMap.put("Mesa 4", mesa4);
        mesaMap.put("Mesa 5", mesa5);

        // Cargar estado inicial
        cargarMesas();

        // Añadir eventos de clic
        mesa1.setOnMouseClicked(e -> toggleMesa("Mesa 1", mesa1));
        mesa2.setOnMouseClicked(e -> toggleMesa("Mesa 2", mesa2));
        mesa3.setOnMouseClicked(e -> toggleMesa("Mesa 3", mesa3));
        mesa4.setOnMouseClicked(e -> toggleMesa("Mesa 4", mesa4));
        mesa5.setOnMouseClicked(e -> toggleMesa("Mesa 5", mesa5));
    }

    private void cargarMesas() {
        RetrofitClient.getApi().getMesas().enqueue(new Callback<List<Mesa>>() {
            @Override
            public void onResponse(Call<List<Mesa>> call, Response<List<Mesa>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Platform.runLater(() -> {
                        for (Mesa mesa : response.body()) {
                            HBox hbox = mesaMap.get(mesa.nombre);
                            if (hbox != null) {
                                actualizarEstilo(hbox, mesa.ocupada);
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Mesa>> call, Throwable t) {
                System.err.println("Error cargando mesas: " + t.getMessage());
            }
        });
    }

    private void toggleMesa(String nombre, HBox hbox) {
        boolean nuevoEstado = !esOcupadaActual(hbox);
        Mesa mesa = new Mesa(nombre, nuevoEstado);

        RetrofitClient.getApi().ocuparMesa(nombre, mesa).enqueue(new Callback<Mesa>() {
            @Override
            public void onResponse(Call<Mesa> call, Response<Mesa> response) {
                if (response.isSuccessful()) {
                    Platform.runLater(() -> actualizarEstilo(hbox, nuevoEstado));
                }
            }

            @Override
            public void onFailure(Call<Mesa> call, Throwable t) {
                System.err.println("Error actualizando mesa: " + t.getMessage());
            }
        });
    }

    private boolean esOcupadaActual(HBox hbox) {
        return hbox.getStyleClass().contains("ocupada");
    }

    private void actualizarEstilo(HBox hbox, boolean ocupada) {
        hbox.getStyleClass().removeAll("libre", "ocupada");
        hbox.getStyleClass().add(ocupada ? "ocupada" : "libre");
    }
}