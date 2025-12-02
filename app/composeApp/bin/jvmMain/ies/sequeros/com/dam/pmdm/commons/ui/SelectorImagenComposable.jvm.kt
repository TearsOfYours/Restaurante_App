package ies.sequeros.com.dam.pmdm.commons.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import java.awt.FileDialog
import java.awt.Frame
import java.io.File
import java.io.FilenameFilter


@Composable
actual fun SelectorImagenComposable(onImageSelected: (String) -> Unit) {
    val file = remember { mutableStateOf<File?>(null)}
    Row() {
        Button(onClick = {
            val dialogo=FileDialog(null as Frame?, "Seleccionar imagen")

            dialogo.filenameFilter = FilenameFilter { _, nombre ->
                nombre.lowercase().endsWith(".png") ||
                        nombre.lowercase().endsWith(".jpg") ||
                        nombre.lowercase().endsWith(".jpeg") ||
                        nombre.lowercase().endsWith(".gif") ||
                        nombre.lowercase().endsWith(".bmp")
            }
            val ffile = dialogo.apply { isVisible = true }

            if (ffile.file != null && ffile.directory != null) {
                val path = ffile.directory + ffile.file
                file.value=ffile.files.get(0)
                onImageSelected(path)
            }
        }) {
            Text("Seleccionar imagen")
        }

    }
}