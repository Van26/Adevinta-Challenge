package cmm.apps.adevinta.view.di

import cmm.apps.adevinta.domain.user.model.User
import cmm.apps.adevinta.view.screens.userlist.UserListViewModel
import cmm.apps.adevinta.view.MainViewModel
import cmm.apps.adevinta.view.screens.userdetails.UserDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewDIModule {

    val module = module {
        viewModel { MainViewModel() }
        viewModel { UserListViewModel(get()) }
        viewModel { (user: User) -> UserDetailsViewModel(user) }
    }

}
