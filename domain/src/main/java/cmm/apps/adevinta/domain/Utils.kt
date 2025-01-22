package cmm.apps.adevinta.domain

import java.net.URLEncoder
import java.nio.charset.StandardCharsets


fun String.encodeUrlForNavigation(): String {
    return URLEncoder.encode(this, StandardCharsets.UTF_8.toString())
}