package cmm.apps.designsystem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cmm.apps.designsystem.utils.decodeUrlFromNavigation
import coil3.compose.AsyncImage

@Composable
fun AdevintaCardInfo(
    model: AdevintaCardInfoModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick.invoke() },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors().copy(
            contentColor = MaterialTheme.colorScheme.onPrimary,
            containerColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier.size(70.dp),
                contentScale = ContentScale.Crop,
                model = model.imageUrl?.decodeUrlFromNavigation(),
                contentDescription = "Translated description of what the image contains",
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                AdevintaText(model.mainText, style = AdevintaTextStyle.HEADING_2, modifier = Modifier.padding(4.dp))
                AdevintaText(model.secondaryText, style = AdevintaTextStyle.BODY_1, modifier = Modifier.padding(4.dp))
                AdevintaText(model.tertiaryText, style = AdevintaTextStyle.BODY_1, modifier = Modifier.padding(4.dp))
            }
        }
    }
}

data class AdevintaCardInfoModel(
    val mainText: String,
    val secondaryText: String,
    val tertiaryText: String,
    val imageUrl: String?
)