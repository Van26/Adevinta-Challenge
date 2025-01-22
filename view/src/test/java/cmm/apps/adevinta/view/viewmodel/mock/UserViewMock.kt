package cmm.apps.adevinta.view.viewmodel.mock

import cmm.apps.adevinta.domain.user.model.Location
import cmm.apps.adevinta.domain.user.model.User

object UserViewMock {

    fun provideUserList(nameList: List<String>): List<User> = nameList.map { name -> provideUser(name) }

    fun provideUser(name: String): User = User(
        uuid = "uuid",
        firstName = name,
        lastName = "lastName",
        email = "email",
        phone = "phone",
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