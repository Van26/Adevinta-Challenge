package cmm.apps.adevinta.datasource_remote.di

import cmm.apps.adevinta.data.di.DataDIModule
import cmm.apps.adevinta.data.user.datasource.UserDatasource
import cmm.apps.adevinta.datasource_remote.api.AdevintaUserApi
import cmm.apps.adevinta.datasource_remote.api.NetworkApiHelper
import cmm.apps.adevinta.datasource_remote.user.UserRemoteDatasourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

object RemoteDIModule {

    val module = module {
        single {
            NetworkApiHelper().provideApi(
                baseUrl = NetworkApiHelper.adevintaApiBaseUrl(),
                clazz = AdevintaUserApi::class.java
            )
        }
        factory<UserDatasource>(named(DataDIModule.REMOTE_DATASOURCE_INSTANCE_NAME)) { UserRemoteDatasourceImpl(get()) }
    }

}