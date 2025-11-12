package org.example.appescritoriotacopaco;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface Api {
    @GET("mesas")
    Call<List<Mesa>> getMesas();

    @Headers("Content-Type: application/json")
    @PUT("mesas/{nombre}")
    Call<Mesa> ocuparMesa(
            @Path("nombre") String nombre,
            @Body Mesa mesa
    );

}
