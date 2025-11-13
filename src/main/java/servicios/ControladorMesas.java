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

    public void cargarMesas() {
        Call<List<Mesa>> call = RetrofitClient.getApi().getMesas();
        call.enqueue(new Callback<List<Mesa>>() {
            @Override
            public void onResponse(Call<List<Mesa>> call, Response<List<Mesa>> response) {
                if (response.isSuccessful()) {
                    List<Mesa> mesas = response.body();
                    Platform.runLater(() -> {
                        for (Mesa mesa : mesas) {
                            HBox mesaBox = getMesaBoxByName(mesa.nombre);
                            if (mesaBox != null) {
                                if (mesa.ocupada) {
                                    mesaBox.setStyle("-fx-background-color: red;");
                                } else {
                                    mesaBox.setStyle("-fx-background-color: green;");
                                }
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Mesa>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    private HBox getMesaBoxByName(String nombre) {
        switch (nombre) {
            case "Mesa 1": return mesa1;
            case "Mesa 2": return mesa2;
            case "Mesa 3": return mesa3;
            case "Mesa 4": return mesa4;
            case "Mesa 5": return mesa5;
            default: return null;
        }
    }

    @FXML
    public void initialize() {
        cargarMesas();
    }

}