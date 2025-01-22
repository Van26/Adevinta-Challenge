package cmm.apps.adevinta.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import cmm.apps.adevinta.view.navigation.AdevintaNavigationGraph
import cmm.apps.adevinta.view.theme.AdevintaTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                setupNavigation()
            }
        }

        splashScreen.setKeepOnScreenCondition { false } // splash loading

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }

    private fun setupNavigation() {
        setContent {
            AdevintaTheme {
                val navigationController = rememberNavController()
                HomeView {
                    AdevintaNavigationGraph(navigationController = navigationController)
                }
            }
        }
    }
}

@Composable
fun HomeView(content: @Composable () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(
                bottom = innerPadding.calculateBottomPadding(),
            )
        ) {
            content.invoke()
        }
    }
}
