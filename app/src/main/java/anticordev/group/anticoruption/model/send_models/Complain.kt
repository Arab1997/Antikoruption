package anticordev.group.anticoruption.model.send_models

import com.google.gson.annotations.SerializedName

data class Complain(
    var amount: Int = 1,
    var area: Int = 1,
    var button_type: Int = 1,
    var currency: Int = 1,
    var organization: Int = 1,
    var region: Int = 1,
    var text: String = ""
)