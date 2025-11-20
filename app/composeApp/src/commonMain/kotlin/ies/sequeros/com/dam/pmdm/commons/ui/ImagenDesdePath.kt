package ies.sequeros.com.dam.pmdm.commons.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.DrawableResource

@Composable
expect fun ImagenDesdePath(path: State<String>,
                           default: DrawableResource,
                           modifier: Modifier=Modifier)