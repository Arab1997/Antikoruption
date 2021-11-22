package profitdevs.group.anticor.model.send_models

import java.io.File

/**
 * @author Zokirjon
 * @date 11/16/2021
 */
data class Complain(
    var region: String = "",
    var area: String = "",
    var organization: String = "",
    val organization_category: Int = 1,
    var amount: Int = 0,
    var text: String = "",
    var email: String = ""
)