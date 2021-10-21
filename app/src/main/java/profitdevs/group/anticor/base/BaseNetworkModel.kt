package profitdev.group.eantikor.base

data class BaseNetworkModel<T>(
    val success: Boolean?,
    val message: String?,
    val data: T?,
    val errorCode: Int?
)