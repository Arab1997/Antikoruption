package anticordev.group.anticoruption.model

data class LoginConfirmResponse(
    val token: String,
    val tuman: String?
)