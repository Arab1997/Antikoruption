package profitdevops.group.anticor.model.getToken


import com.google.gson.annotations.SerializedName

data class GetTokenResponse(
    @SerializedName("token")
    val token: String
)