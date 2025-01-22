package cmm.apps.adevinta.view.screenshot.userlist

import cmm.apps.adevinta.view.screens.userlist.UserListView
import cmm.apps.adevinta.view.screens.userlist.model.UserListUiState
import cmm.apps.adevinta.view.screenshot.BaseScreenshotTest
import cmm.apps.adevinta.view.theme.AdevintaTheme
import cmm.apps.designsystem.AdevintaCardInfoModel
import org.junit.Test

class UserListViewScreenshotTest : BaseScreenshotTest() {

    @Test
    fun userListView_lightTheme_empty() {
        snapshotWithState()
    }

    private fun snapshotWithState() {
        paparazzi.snapshot {
            AdevintaTheme(darkTheme = false) {
                UserListView(
                    uiState = UserListUiState(
                        userList = listOf(
                            AdevintaCardInfoModel(
                                mainText = "Adrian Diaz",
                                secondaryText = "adr26lineas@gmail.com",
                                tertiaryText = "+34 666 666 666",
                                imageUrl = null
                            )
                        )
                    ),
                    onUserItemClicked = { },
                    onLoadMoreUsers = { }
                )
            }
        }
    }

}