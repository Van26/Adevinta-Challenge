package cmm.apps.adevinta.view.screenshot.userdetails

import cmm.apps.adevinta.view.screens.userdetails.UserDetailsView
import cmm.apps.adevinta.view.screens.userdetails.model.UserDetailsUiState
import cmm.apps.adevinta.view.screenshot.BaseScreenshotTest
import cmm.apps.adevinta.view.theme.AdevintaTheme
import org.junit.Test

class UserDetailsScreenshotTest : BaseScreenshotTest() {

    @Test
    fun userDetailsView_lightTheme_data() {
        snapshotWithState()
    }

    private fun snapshotWithState() {
        paparazzi.snapshot {
            AdevintaTheme(darkTheme = false) {
                UserDetailsView(
                    uiState = UserDetailsUiState(
                        pictureUrl = null,
                        gender = "male",
                        name = "Adrian Diaz",
                        direction = "ESP",
                        registeredDate = "10/10/1010 10:10",
                        email = "adr26lineas@gmail.com"
                    )
                )
            }
        }
    }

}