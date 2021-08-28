package apps.saa.networking.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Order(
    @SerializedName("address")
    val address: String,
    @SerializedName("customer_lang")
    val customerLang: Double,
    @SerializedName("customer_lat")
    val customerLat: Double,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("shop_lang")
    val shopLang: Double,
    @SerializedName("shop_lat")
    val shopLat: Double
): Serializable