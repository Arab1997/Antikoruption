package profitdevs.group.anticor.model.send_models

data class Complain(
    var region: Int = 0,
    var area: Int = 0,
    var organization: String = "",
    val organization_category: Int = 1,
    var amount: Int = 0,
    var button_type: Int = 0,
    var text: String = "",
    var email: String = ""
)