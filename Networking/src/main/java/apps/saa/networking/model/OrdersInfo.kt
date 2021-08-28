package apps.saa.networking.model


import com.google.gson.annotations.SerializedName

data class OrdersInfo(
    @SerializedName("heading-text")
    val headingText: String,
    @SerializedName("logo-url")
    val logoUrl: String,
    @SerializedName("orders")
    val orders: List<Order>
)