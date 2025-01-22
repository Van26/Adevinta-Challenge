package cmm.apps.adevinta.domain.mock

import cmm.apps.adevinta.domain.user.model.Location
import cmm.apps.adevinta.domain.user.model.User

object UserDomainMock {

    fun provideUserList(nameList: List<String>): List<User> = nameList.map { name -> provideUser(name) }

    fun provideUser(firstName: String = "Adrian", lastName: String = "Diaz", email: String = "adr26lineas@gmail.com") = User(
        uuid = "uuid",
        firstName = firstName,
        lastName = lastName,
        email = email,
        phone = "666 666 666",
        imageUrl = "imageUrl",
        gender = "gender",
        title = "title",
        location = Location(
            streetName = "streetName",
            streetNumber = 1,
            city = "city",
            state = "state",
            postcode = "postcode",
            country = "country"
        ),
        registeredDateTime = 1000
    )
}
