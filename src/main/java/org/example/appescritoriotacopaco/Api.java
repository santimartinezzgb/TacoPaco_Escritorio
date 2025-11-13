package org.example.appescritoriotacopaco;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import java.util.List;
import java.util.Map;

public interface Api {

    @GET("mesas")
    Call<List<Mesa>> getMesas();

    @PUT("mesas/{nombre}")
    Call<Mesa> ocuparMesa(@Path("nombre") String nombre, @Body Map<String, Boolean> estado);

    @POST("pedidos")
    Call<Pedido> crearPedido(@Body Pedido pedido);
}
