package cmm.apps.adevinta.datasource_remote.user.model

import com.google.gson.annotations.SerializedName

data class UserListResultRemoteModel(
    @SerializedName("results") val results: List<UserRemoteModel>,
)

data class UserRemoteModel(
    @SerializedName("login") val profile: ProfileRemoteModel,
    @SerializedName("name") val name: NameRemoteModel,
    @SerializedName("location") val location: LocationRemoteModel,
    @SerializedName("gender") val gender: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("picture") val picture: PictureRemoteModel?,
    @SerializedName("registered") val registered: RegisteredRemoteModel
)

data class ProfileRemoteModel(
    @SerializedName("uuid") val uuid: String?
)

data class NameRemoteModel(
    @SerializedName("title") val title: String,
    @SerializedName("first") val firstName: String,
    @SerializedName("last") val lastName: String
)

data class LocationRemoteModel(
    @SerializedName("street") val street: StreetRemoteModel,
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String,
    @SerializedName("country") val country: String,
    @SerializedName("postcode") val postcode: String
)

data class StreetRemoteModel(
    @SerializedName("number") val streetNumber: Int,
    @SerializedName("name") val streetName: String
)

data class PictureRemoteModel(
    @SerializedName("medium") val imageUrl: String?
)

data class RegisteredRemoteModel(
    @SerializedName("date") val date: String
)
