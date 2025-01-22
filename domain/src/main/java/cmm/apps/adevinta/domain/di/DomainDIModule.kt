package cmm.apps.adevinta.domain.di

import cmm.apps.adevinta.domain.user.GetSavedUserUseCase
import cmm.apps.adevinta.domain.user.GetSavedUserUseCaseImpl
import org.koin.dsl.module


object DomainDIModule {

    val module = module {
        factory<GetSavedUserUseCase> { GetSavedUserUseCaseImpl(get()) }
    }

}