package cmm.apps.adevinta.datasource_local.user.mapper

import cmm.apps.adevinta.data.user.model.UserDataModel
import cmm.apps.adevinta.datasource_local.user.model.UserLocalModel

fun UserLocalModel.toUserDataModel(): UserDataModel = UserDataModel(
    uuid = uuid,
    firstName = firstName,
    lastName = lastName,
    email = email,
    phone = phone,
    imageUrl = imageUrl,
    gender = gender,
    title = title,
    streetName = streetName,
    streetNumber = streetNumber,
    city = city,
    state = state,
    postcode = postcode,
    country = country
)

fun UserDataModel.toUserLocalModel(): UserLocalModel = UserLocalModel(
    uuid = uuid,
    firstName = firstName,
    lastName = lastName,
    email = email,
    phone = phone,
    imageUrl = imageUrl,
    gender = gender,
    title = title,
    streetName = streetName,
    streetNumber = streetNumber,
    city = city,
    state = state,
    postcode = postcode,
    country = country
)