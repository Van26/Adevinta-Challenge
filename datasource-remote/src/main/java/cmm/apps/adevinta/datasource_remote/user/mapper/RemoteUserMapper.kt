package cmm.apps.adevinta.datasource_remote.user.mapper

import cmm.apps.adevinta.data.user.model.UserDataModel
import cmm.apps.adevinta.datasource_remote.user.model.UserRemoteModel

fun UserRemoteModel.toUserDataModel(): UserDataModel {
    return UserDataModel(
        uuid = uuid ?: "",
        firstName = name.firstName,
        lastName = name.lastName,
        email = email,
        phone = phone,
        imageUrl = imageUrl,
        gender = gender,
        title = name.title,
        streetName = location.street.streetName,
        streetNumber = location.street.streetNumber,
        city = location.city,
        state = location.state,
        postcode = location.postcode,
        country = location.country
    )
}