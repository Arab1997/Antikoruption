package profitdevs.group.anticor.model.send_models

import java.io.File

/**
 * @author Zokirjon
 * @date 11/16/2021
 */
data class Complain(
    val region: Region,
    val area: Area,
    val organization: Organization,
    val organization_category: OrganizationCategory,
    val amount: Int,
    val text: String,
    val file: File? = null,
    val email: String? = null
)