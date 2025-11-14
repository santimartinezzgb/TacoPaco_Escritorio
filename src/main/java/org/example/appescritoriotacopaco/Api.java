package org.example.appescritoriotacopaco;

import com.sun.javafx.collections.MappingChange;
import retrofit2.Call;
import retrofit2.http.*;
import java.util.List;
import java.util.Map;

public interface Api {
    @GET("mesas")
    Call<List<Mesa>> getMesas();

    @GET("pedidos")
    Call<List<Pedido>> getPedidos();

    @PUT("mesas/{nombre}")
    Call<Mesa> ocuparMesa(@Path("nombre") String nombre, @Body Mesa mesa);
}
