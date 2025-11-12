package org.example.appescritoriotacopaco;

import retrofit2.Call;
import retrofit2.http.*;
import java.util.List;

public interface Api {
    @GET("mesas")
    Call<List<Mesa>> getMesas();

    @PUT("mesas/{nombre}")
    @Headers("Content-Type: application/json")
    Call<Mesa> ocuparMesa(@Path("nombre") String nombre, @Body Mesa mesa);
}