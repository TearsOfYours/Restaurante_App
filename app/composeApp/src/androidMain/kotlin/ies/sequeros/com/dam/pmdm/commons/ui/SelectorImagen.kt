package ies.sequeros.com.dam.pmdm.commons.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun SelectorImagen(onImageSelected: (Uri) -> Unit) {
   val myuri= remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            myuri.value=uri
            onImageSelected(uri)
        }
    }
    Row {

    Button(onClick = {
        launcher.launch("*")//"image/*")   // abre la galería
    }) {
        Text("Seleccionar imagen")
    }

    }
}