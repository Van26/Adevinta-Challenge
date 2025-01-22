package cmm.apps.adevinta.domain.user.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val uuid: String?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val imageUrl: String?,
    val gender: String,
    val title: String,
    val location: Location,
    val registeredDateTime: Long
)

@Serializable
data class Location(
    val streetName: String?,
    val streetNumber: Int?,
    val city: String?,
    val state: String?,
    val postcode: String?,
    val country: String?
) {
    fun getFullAddress(): String {
        return formatNullableStrings("$streetName $streetNumber".trim(), city, state, postcode, country)
    }

    private fun formatNullableStrings(vararg strings: String?): String {
        return strings.filter { !it.isNullOrBlank() }.joinToString(", ")
    }
}
