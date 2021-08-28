package apps.saa.networking

import apps.saa.networking.model.OrdersInfo
import apps.saa.networking.model.UIInfo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "http://3.6.99.194/mock_api/"

interface ApiService {
    @GET("form_controls")
    fun fetchDynamicForm(): Call<UIInfo>

    @GET("orders")
    fun fetchOrders(): Call<OrdersInfo>
}

object getRetrofitObj {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)
}

