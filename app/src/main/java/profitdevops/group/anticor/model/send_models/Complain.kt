package profitdevops.group.anticor.model.send_models

data class Complain(
    var amount: Int = 1,
    var area: Int = 1,
    var button_type: Int = 1,
    var currency: Int = 1,
    var organization: Int = 1,
    var region: Int = 1,
    var text: String = ""
)