package cmm.apps.adevinta.datasource_local.di

import android.content.Context
import android.content.SharedPreferences
import cmm.apps.adevinta.data.di.DataDIModule
import cmm.apps.adevinta.data.user.datasource.UserDatasource
import cmm.apps.adevinta.datasource_local.database.AdevintaDatabase
import cmm.apps.adevinta.datasource_local.database.AdevintaDatabaseHelper
import cmm.apps.adevinta.datasource_local.database.dao.UserDao
import cmm.apps.adevinta.datasource_local.user.UserLocalDatasourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

object LocalDIModule {

    private fun adevintaDatabase(context: Context) = AdevintaDatabaseHelper.getDatabase(context)

    val module = module {
        single { adevintaDatabase(get()) }
        single<SharedPreferences> {
            val context: Context = get()
            context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        }
        single<UserDao> { get<AdevintaDatabase>().userDao() }
        factory<UserDatasource>(named(DataDIModule.LOCAL_DATASOURCE_INSTANCE_NAME)) { UserLocalDatasourceImpl(get(), get()) }
    }

}