package cmm.apps.adevinta.datasource_remote.api

import cmm.apps.adevinta.datasource_remote.user.model.UserListResultRemoteModel
import retrofit2.http.GET

interface AdevintaUserApi {

    @GET("?results=40")
    suspend fun getUserList(): UserListResultRemoteModel

}