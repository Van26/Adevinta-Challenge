package cmm.apps.adevinta.view.screens.userlist

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmm.apps.adevinta.domain.user.model.User
import cmm.apps.adevinta.view.Screen
import cmm.apps.adevinta.view.screens.userlist.UserListScreenTestTags.USER_LIST_CONTENT
import cmm.apps.adevinta.view.theme.AdevintaTheme
import cmm.apps.adevinta.view.screens.userlist.model.UserListEffect
import cmm.apps.adevinta.view.screens.userlist.model.UserListUiState
import cmm.apps.designsystem.AdevintaCardInfo
import cmm.apps.designsystem.AdevintaCardInfoModel
import cmm.apps.designsystem.AdevintaCircularLoader
import org.koin.androidx.compose.koinViewModel

@Screen
@Composable
fun UserListScreen(ulvm: UserListViewModel = koinViewModel(), onUserClicked: (user: User) -> Unit) {
    val uiState by ulvm.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        ulvm.effect.collect { eff ->
            when (eff) {
                is UserListEffect.NavigateToUserDetails -> onUserClicked(eff.user)
            }
        }
    }
    AdevintaTheme {
        UserListView(
            uiState = uiState,
            onUserItemClicked = { position -> ulvm.onUserItemClicked(position) },
            onLoadMoreUsers = { ulvm.loadMoreUsers() }
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserListView(
    uiState: UserListUiState,
    onUserItemClicked: (position: Int) -> Unit,
    onLoadMoreUsers: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {}
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 0.dp,
                    bottom = 0.dp
                )
                .fillMaxSize()
                .testTag(USER_LIST_CONTENT)
        ) {
            Image(
                painter = painterResource(id = uiState.icon), contentDescription = "App logo",
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(32.dp))
            )
            LazyColumnWithEndDetection(uiState.userList, onUserItemClicked = {
                onUserItemClicked(it)
            }) {
                onLoadMoreUsers()
            }
        }
    }
}

@Composable
fun LazyColumnWithEndDetection(userList: List<AdevintaCardInfoModel>, onUserItemClicked: (position: Int) -> Unit, onBottomScrollReached: () -> Unit) {
    val lazyListState = rememberLazyListState()

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                val lastVisibleItemIndex = visibleItems.lastOrNull()?.index
                if (lastVisibleItemIndex == lazyListState.layoutInfo.totalItemsCount - 1 || lastVisibleItemIndex == null) {
                    onBottomScrollReached()
                }
            }
    }

    LazyColumn(
        state = lazyListState,
        modifier = Modifier.fillMaxSize()
    ) {
        items(userList.size) { itemPosition ->
            AdevintaCardInfo(
                userList[itemPosition],
                onClick = { onUserItemClicked(itemPosition) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                AdevintaCircularLoader()
            }
        }
    }
}

object UserListScreenTestTags {
    const val USER_LIST_CONTENT = "user list content"
}