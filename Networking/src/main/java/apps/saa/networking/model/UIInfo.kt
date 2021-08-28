package apps.saa.networking.model


import com.google.gson.annotations.SerializedName

data class UIInfo(
    @SerializedName("heading-text")
    val headingText: String,
    @SerializedName("logo-url")
    val logoUrl: String,
    @SerializedName("uidata")
    val uidata: List<Uidata>
)