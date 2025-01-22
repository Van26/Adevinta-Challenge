package cmm.apps.designsystem.utils

import java.net.URLDecoder
import java.nio.charset.StandardCharsets

fun String.decodeUrlFromNavigation(): String {
    return URLDecoder.decode(this, StandardCharsets.UTF_8.toString())
}