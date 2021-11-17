package profitdevs.group.anticor.model.send_models

/**
 * @author Zokirjon
 * @date 11/17/2021
 */
data class Region(
    val id: Int,
    val name: String,
    val areas: List<Area>
)
