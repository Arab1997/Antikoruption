package profitdevops.group.anticor.model.send_models

data class Compliment(
    val amount: Int = 1,
    val area: Int = 1,
    val button_type: Int = 1,
    val currency: Int = 1,
    val is_individual: Int = 1,
    val organization: Int = 1,
    val region: Int = 1,
    val text: String = "test"
)