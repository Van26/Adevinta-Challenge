package cmm.apps.adevinta.component.mock

import cmm.apps.adevinta.data.user.model.UserDataModel


object UserDataMock {

    fun provideUserDataModelList(nameList: List<String>): List<UserDataModel> = nameList.map { name -> provideUserDataModel(name) }

    fun provideUserDataModel(firstName: String = "Adrian"): UserDataModel = UserDataModel(
        uuid = "uuid",
        firstName = firstName,
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
        registeredDate = "2002-07-18T08:04:42.718Z"
    )

}