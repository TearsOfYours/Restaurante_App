package ies.sequeros.com.dam.pmdm.administrador.ui.dependientes.changepassordform

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import ies.sequeros.com.dam.pmdm.administrador.ui.dependientes.DependientesViewModel


@Composable
fun CambiarClave(
    dependientesViewModel: DependientesViewModel,
    onClose: () -> Unit,
    function: () -> Unit
) {
    Text("holaaa", fontSize = (30.sp))
}