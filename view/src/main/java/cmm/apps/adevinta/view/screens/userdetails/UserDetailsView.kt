package cmm.apps.adevinta.view.screens.userdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmm.apps.adevinta.domain.user.model.User
import cmm.apps.adevinta.view.Screen
import cmm.apps.adevinta.view.screens.userdetails.UserDetailsScreenTestTags.USER_DETAILS_CONTENT
import cmm.apps.adevinta.view.screens.userdetails.model.UserDetailsUiState
import cmm.apps.adevinta.view.theme.AdevintaTheme
import cmm.apps.designsystem.AdevintaText
import cmm.apps.designsystem.AdevintaTextStyle
import cmm.apps.designsystem.utils.decodeUrlFromNavigation
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Screen
@Composable
fun UserDetailsScreen(
    user: User,
    udvm: UserDetailsViewModel = koinViewModel(parameters = { parametersOf(user) })
) {
    val uiState by udvm.uiState.collectAsStateWithLifecycle()

    AdevintaTheme {
        UserDetailsView(uiState)
    }
}

@Composable
fun UserDetailsView(uiState: UserDetailsUiState) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {}
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                )
                .fillMaxSize()
                .testTag(USER_DETAILS_CONTENT),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            AsyncImage(
                modifier = Modifier.size(120.dp).clip(CircleShape),
                contentScale = ContentScale.Crop,
                model = uiState.pictureUrl?.decodeUrlFromNavigation(),
                contentDescription = "Translated description of what the image contains",
            )
            AdevintaText(
                uiState.gender,
                style = AdevintaTextStyle.BODY_1,
                modifier = Modifier.padding(4.dp, top = 8.dp),
                textAlign = TextAlign.Center
            )
            AdevintaText(
                uiState.name,
                style = AdevintaTextStyle.HEADING_2,
                modifier = Modifier.padding(4.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            AdevintaText(
                uiState.direction,
                style = AdevintaTextStyle.BODY_1,
                modifier = Modifier.padding(4.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            AdevintaText(
                uiState.registeredDate,
                style = AdevintaTextStyle.BODY_1,
                modifier = Modifier.padding(4.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            AdevintaText(
                uiState.email,
                style = AdevintaTextStyle.BODY_1,
                modifier = Modifier.padding(4.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

object UserDetailsScreenTestTags {
    const val USER_DETAILS_CONTENT = "user details content"
}