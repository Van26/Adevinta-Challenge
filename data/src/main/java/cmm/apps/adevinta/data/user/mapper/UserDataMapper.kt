package cmm.apps.adevinta.data.user.mapper

import cmm.apps.adevinta.data.user.model.UserDataModel
import cmm.apps.adevinta.domain.encodeUrlForNavigation
import cmm.apps.adevinta.domain.user.model.Location
import cmm.apps.adevinta.domain.user.model.User
import java.time.Instant

fun UserDataModel.toUser() = User(
    uuid = uuid,
    firstName = firstName,
    lastName = lastName,
    email = email,
    phone = phone,
    imageUrl = imageUrl?.encodeUrlForNavigation(),
    gender = gender,
    title = title,
    location = Location(
        streetName = streetName,
        streetNumber = streetNumber,
        city = city,
        state = state,
        postcode = postcode,
        country = country
    ),
    registeredDateTime = Instant.parse(registeredDate).toEpochMilli()
)
