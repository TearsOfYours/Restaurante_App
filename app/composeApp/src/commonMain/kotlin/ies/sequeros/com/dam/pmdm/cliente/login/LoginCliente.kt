package ies.sequeros.com.dam.pmdm.cliente.login

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ies.sequeros.com.dam.pmdm.AppViewModel

@Composable
fun LoginCliente(
    appViewModel: AppViewModel,
    loginClienteViewModel: LoginClienteViewModel,
    onLogin: () -> Unit) {
    Text("Login del cliente")

    Button(onClick = {
        onLogin()
    }) {}
}