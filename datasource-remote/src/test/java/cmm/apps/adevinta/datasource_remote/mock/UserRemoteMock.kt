package cmm.apps.adevinta.datasource_remote.mock

import cmm.apps.adevinta.datasource_remote.user.model.LocationRemoteModel
import cmm.apps.adevinta.datasource_remote.user.model.NameRemoteModel
import cmm.apps.adevinta.datasource_remote.user.model.PictureRemoteModel
import cmm.apps.adevinta.datasource_remote.user.model.ProfileRemoteModel
import cmm.apps.adevinta.datasource_remote.user.model.RegisteredRemoteModel
import cmm.apps.adevinta.datasource_remote.user.model.StreetRemoteModel
import cmm.apps.adevinta.datasource_remote.user.model.UserListResultRemoteModel
import cmm.apps.adevinta.datasource_remote.user.model.UserRemoteModel

object UserRemoteMock {

    fun provideUser(name: String): UserListResultRemoteModel = UserListResultRemoteModel(
        results = listOf(
            UserRemoteModel(
                profile = ProfileRemoteModel("uuid"),
                name = NameRemoteModel(
                    "title",
                    name,
                    "lastName"
                ),
                location = LocationRemoteModel(
                    StreetRemoteModel(
                        1,
                        "streetName"
                    ),
                    "city",
                    "state",
                    "postcode",
                    "country"
                ),
                gender = "gender",
                email = "email",
                phone = "phone",
                picture = PictureRemoteModel("imageUrl"),
                registered = RegisteredRemoteModel("date")
            )
        )
    )
}