package cmm.apps.adevinta.data.user.model

data class UserDataModel(
    val uuid: String?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val imageUrl: String?,
    val gender: String,
    val title: String,
    val streetName: String?,
    val streetNumber: Int?,
    val city: String?,
    val state: String?,
    val postcode: String?,
    val country: String?,
    val registeredDate: String
)