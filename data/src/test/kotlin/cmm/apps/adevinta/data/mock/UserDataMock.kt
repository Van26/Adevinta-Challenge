package cmm.apps.adevinta.data.mock

import cmm.apps.adevinta.data.user.model.UserDataModel

object UserDataMock {

    fun provideUserDataModel(name: String = "Adrian", lastName: String = "Diaz", email: String = "adr26lineas@gmail.com"): UserDataModel = UserDataModel(
        uuid = "uuid",
        firstName = name,
        lastName = lastName,
        email = email,
        phone = "phone",
        imageUrl = "imageUrl",
        gender = "gender",
        title = "title",
        streetName = "streetName",
        streetNumber = 1,
        city = "city",
        state = "state",
        postcode = "postcode",
        country = "country",
        registeredDate = "2002-07-18T08:04:42.718Z"
    )
}