package cmm.apps.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AdevintaText(text: String, style: AdevintaTextStyle, modifier: Modifier = Modifier, textAlign: TextAlign = TextAlign.Start) {
    Text(
        text = text,
        style = getTextStyle(style),
        modifier = modifier,
        textAlign = textAlign
    )
}

@Composable
fun getTextStyle(style: AdevintaTextStyle): TextStyle {
    return when (style) {
        AdevintaTextStyle.TITLE -> MaterialTheme.typography.titleLarge
        AdevintaTextStyle.HEADING_1 -> MaterialTheme.typography.headlineLarge
        AdevintaTextStyle.HEADING_2 -> MaterialTheme.typography.headlineMedium
        AdevintaTextStyle.BODY_1 -> MaterialTheme.typography.bodyMedium
        AdevintaTextStyle.BODY_1_ACCENT -> MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
        AdevintaTextStyle.CAPTION -> MaterialTheme.typography.labelSmall
        AdevintaTextStyle.BUTTON -> MaterialTheme.typography.labelLarge
    }
}

enum class AdevintaTextStyle {
    TITLE,
    HEADING_1,
    HEADING_2,
    BODY_1,
    BODY_1_ACCENT,
    CAPTION,
    BUTTON
}