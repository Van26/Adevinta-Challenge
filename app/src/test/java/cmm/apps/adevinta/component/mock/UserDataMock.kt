package cmm.apps.adevinta.component.mock

import cmm.apps.adevinta.data.user.model.UserDataModel


object UserDataMock {

    fun provideUserDataModelList(nameList: List<String>): List<UserDataModel> = nameList.map { provideUserDataModel() }

    fun provideUserDataModel(): UserDataModel = UserDataModel(
        uuid = "uuid",
        firstName = "name",
        lastName = "",
        email = "email",
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
        registeredDate = "date"
    )

}