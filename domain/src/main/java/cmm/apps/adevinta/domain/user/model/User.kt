package cmm.apps.adevinta.domain.user.model

data class User(
    val uuid: String?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val imageUrl: String,
    val gender: String,
    val title: String,
    val location: Location
)

data class Location(
    val streetName: String?,
    val streetNumber: Int?,
    val city: String?,
    val state: String?,
    val postcode: Int?,
    val country: String?
) {
    fun getFullAddress(): String {
        val streetBody1 = streetName?.let { streetNumber?.let { "$streetName $it" } ?: "$streetName" } ?: ""
        val streetBody2 = city?.let { if (streetBody1.isBlank()) ", $it" else it } ?: ""
        val streetBody3 = state?.let { if ((streetBody1 + streetBody2).isBlank()) ", $it" else it } ?: ""
        val streetBody4 =  postcode?.let { if ((streetBody1 + streetBody2 + streetBody3).isBlank()) ", $it" else it } ?: ""
        val streetBody5 =  country?.let { if ((streetBody1 + streetBody2 + streetBody3 + streetBody4).isBlank()) ", $it" else it } ?: ""

        return streetBody1 + streetBody2 + streetBody3 + streetBody4 + streetBody5
    }
}
