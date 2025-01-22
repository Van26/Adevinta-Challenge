package cmm.apps.adevinta.di

import cmm.apps.adevinta.data.di.DataDIModule
import cmm.apps.adevinta.datasource_local.di.LocalDIModule
import cmm.apps.adevinta.datasource_remote.di.RemoteDIModule
import cmm.apps.adevinta.domain.di.DomainDIModule
import cmm.apps.adevinta.view.di.ViewDIModule
import org.koin.dsl.module

object AppDIModules {

    val modules = module {
        includes(
            ViewDIModule.module,
            DomainDIModule.module,
            DataDIModule.module,
            RemoteDIModule.module,
            LocalDIModule.module
        )
    }
}