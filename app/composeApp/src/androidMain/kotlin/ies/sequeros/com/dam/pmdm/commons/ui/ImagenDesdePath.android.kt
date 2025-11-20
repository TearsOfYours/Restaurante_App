package ies.sequeros.com.dam.pmdm.commons.ui

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
actual fun ImagenDesdePath(
    path: State<String>,
    default: DrawableResource,
    modifier: Modifier
) {
    val uri = Uri.parse(path.value)

    if (uri != null && uri.toString().isNotEmpty()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(uri)
            .memoryCachePolicy(CachePolicy.DISABLED)
            .diskCachePolicy(CachePolicy.DISABLED)
            .build(),

            contentDescription = null,
            modifier = modifier
        )
    } else {
        Image(
            painterResource(default),
            contentDescription = null,
            modifier = modifier
        )
    }
}
