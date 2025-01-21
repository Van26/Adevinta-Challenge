package cmm.apps.adevinta.datasource_remote.user.model

import com.google.gson.annotations.SerializedName

data class UserRemoteModel(
    @SerializedName("login.uuid") val uuid: String?,
    @SerializedName("name") val name: NameRemoteModel,
    @SerializedName("location") val location: LocationRemoteModel,
    @SerializedName("gender") val gender: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("picture.medium") val imageUrl: String
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
    @SerializedName("postcode") val postcode: Int
)

data class StreetRemoteModel(
    @SerializedName("number") val streetNumber: Int,
    @SerializedName("name") val streetName: String
)
