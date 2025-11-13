package servicios;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.input.MouseEvent;
import org.example.appescritoriotacopaco.Mesa;
import org.example.appescritoriotacopaco.Pedido;
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
                    Platform.runLater(() -> actualizarMesas(mesas));
                }
            }

            @Override
            public void onFailure(Call<List<Mesa>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void actualizarMesas(List<Mesa> mesas) {
        for (Mesa mesa : mesas) {
            HBox mesaBox = getMesaBoxByName(mesa.nombre);
            if (mesaBox != null) {
                if (mesa.ocupada) {
                    mesaBox.setStyle("-fx-background-color: red;");
                    mesaBox.setOnMouseClicked(e -> cobrarMesa(mesa.nombre));
                } else {
                    mesaBox.setStyle("-fx-background-color: green;");
                    mesaBox.setOnMouseClicked(null); // no hacer nada si está libre
                }
            }
        }
    }

    private void cobrarMesa(String nombreMesa) {
        // Liberar mesa
        Map<String, Boolean> estado = new HashMap<>();
        estado.put("ocupada", false);
        RetrofitClient.getApi().ocuparMesa(nombreMesa, estado).enqueue(new Callback<Mesa>() {
            @Override
            public void onResponse(Call<Mesa> call, Response<Mesa> response) {
                cargarMesas(); // actualizar UI
            }

            @Override
            public void onFailure(Call<Mesa> call, Throwable t) {
                t.printStackTrace();
            }
        });

        // Crear un pedido de ejemplo con precio 0 (o lo que quieras)
        Pedido pedido = new Pedido(0.0);
        RetrofitClient.getApi().crearPedido(pedido).enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                System.out.println("Pedido cobrado: " + response.body());
            }

            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private HBox getMesaBoxByName(String nombre) {
        switch (nombre) {
            case "Mesa1": return mesa1;
            case "Mesa2": return mesa2;
            case "Mesa3": return mesa3;
            case "Mesa4": return mesa4;
            case "Mesa5": return mesa5;
            default: return null;
        }
    }

    @FXML
    public void initialize() {
        cargarMesas();
    }
}
