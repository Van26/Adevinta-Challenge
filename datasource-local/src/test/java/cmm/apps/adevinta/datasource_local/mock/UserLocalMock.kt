package cmm.apps.adevinta.datasource_local.mock

import cmm.apps.adevinta.datasource_local.user.model.UserLocalModel

object UserLocalMock {

    fun provideUser(email: String = "adr26lineas@gmail.com", name: String = "Adrian", lastName: String = "Diaz") = UserLocalModel(
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
        registeredDate = "registeredDate"
    )
}