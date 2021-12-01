package anticordev.group.anticoruption.model.send_models


data class Region(
    val id: Int,
    val name: String,
    val areas: List<Area>
)
