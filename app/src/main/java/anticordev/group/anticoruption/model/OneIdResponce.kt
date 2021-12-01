package anticordev.group.anticoruption.model

data class OneIdResponce(
    val access_token: String,
    val expires_in: Long,
    val refresh_token: String,
    val scope: String,
    val token_type: String,
    val token:String
)