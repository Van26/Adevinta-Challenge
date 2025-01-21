package cmm.apps.adevinta.view.userlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmm.apps.adevinta.view.Screen
import cmm.apps.adevinta.view.theme.AdevintaTheme
import cmm.apps.adevinta.view.userlist.model.UserListEffect
import cmm.apps.adevinta.view.userlist.model.UserListUiState
import cmm.apps.designsystem.AdevintaCardInfo
import cmm.apps.designsystem.AdevintaCardInfoModel
import org.koin.androidx.compose.koinViewModel

@Screen
@Composable
fun UserListScreen(ulvm: UserListViewModel = koinViewModel(), onUserClicked: (position: Int) -> Unit) {
    val uiState: UserListUiState by ulvm.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        ulvm.effect.collect { eff ->
            when (eff) {
                is UserListEffect.NavigateToUserDetails -> onUserClicked(eff.position)
            }
        }
    }
    AdevintaTheme {
        UserListView(ulvm, uiState = uiState, onUserItemClicked = { position -> ulvm.onUserItemClicked(position) })
    }
}

@Composable
fun UserListView(ulvm: UserListViewModel, uiState: UserListUiState, onUserItemClicked: (position: Int) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {}
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                )
                .fillMaxSize()
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
                ulvm.loadMoreUsers()
            }
            Column(modifier = Modifier.fillMaxSize()) {
                uiState.userList.forEachIndexed { position, userModel ->
                    AdevintaCardInfo(
                        AdevintaCardInfoModel(userModel.mainText, userModel.secondaryText, userModel.tertiaryText, userModel.imageUrl),
                        onClick = { onUserItemClicked(position) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun LazyColumnWithEndDetection(userList: List<AdevintaCardInfoModel>, onUserItemClicked: (position: Int) -> Unit, onScrollToEnd: () -> Unit) {
    val lazyListState = rememberLazyListState()

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                val lastVisibleItemIndex = visibleItems.lastOrNull()?.index
                if (lastVisibleItemIndex == lazyListState.layoutInfo.totalItemsCount - 1 || lastVisibleItemIndex == null) {
                    onScrollToEnd()
                }
            }
    }

    LazyColumn(
        state = lazyListState,
        modifier = Modifier.fillMaxSize()
    ) {
        items(userList.size) { item ->
            AdevintaCardInfo(
                userList[item],
                onClick = { onUserItemClicked(item) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
