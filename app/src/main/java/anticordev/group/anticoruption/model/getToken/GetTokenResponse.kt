package anticordev.group.anticoruption.model.getToken


import com.google.gson.annotations.SerializedName

data class GetTokenResponse(
    @SerializedName("token")
    val token: String
)

data class UploadFileResponse(
    val message:String
)