package cmm.apps.adevinta.view.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val LightColorScheme = lightColorScheme(
    primary = Blue,
    onPrimary = DarkBlue,
    secondary = Pink,
    onSecondary = DarkGrey,
    background = Lavender,
    onBackground = DarkGrey,
    surface = LightBlue,
    surfaceVariant = Pearl,
    surfaceContainerLow = WhiteSmoke,
    surfaceContainerLowest = White,
    onSurface = DarkGrey,
    onSurfaceVariant = Sepia
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkBlue,
    onPrimary = VeryLightGrey,
    secondary = DarkPink,
    onSecondary = SoftDarkGrey,
    background = DarkLavender,
    onBackground = VeryLightGrey,
    surface = DarkLavender,
    surfaceVariant = DarkPearl,
    surfaceContainerLow = NightRider,
    surfaceContainerLowest = VeryDarkGrey,
    onSurface = VeryLightGrey,
    onSurfaceVariant = LightSepia
)

@Composable
fun AdevintaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = getAdevintaTypography(colorScheme),
        content = content
    )
}