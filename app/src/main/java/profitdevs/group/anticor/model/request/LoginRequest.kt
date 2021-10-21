package profitdev.group.eantikor.model.request

data class LoginRequest(
    val phone: String,
    val code: String
)