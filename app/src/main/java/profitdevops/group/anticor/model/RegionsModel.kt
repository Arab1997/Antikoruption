package profitdevops.group.anticor.model

import java.io.Serializable

data class RegionsModel(
    val id: String,
    val name: String,
    val items: Int,
    val image: String?
): Serializable