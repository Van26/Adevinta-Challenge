package cmm.apps.adevinta.data.di

import cmm.apps.adevinta.data.user.UserRepositoryImpl
import cmm.apps.adevinta.domain.user.repository.UserRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DataDIModule {

    const val LOCAL_DATASOURCE_INSTANCE_NAME = "LocalDatasourceInstance"
    const val REMOTE_DATASOURCE_INSTANCE_NAME = "RemoteDatasourceInstance"

    val module = module {
        factory<UserRepository> {
            UserRepositoryImpl(
                get(named(LOCAL_DATASOURCE_INSTANCE_NAME)),
                get(named(REMOTE_DATASOURCE_INSTANCE_NAME))
            )
        }
    }

}