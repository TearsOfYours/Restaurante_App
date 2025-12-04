package ies.sequeros.com.dam.pmdm.dependiente.login

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import ies.sequeros.com.dam.pmdm.AppViewModel
import ies.sequeros.com.dam.pmdm.dependiente.DependienteRoutes
import ies.sequeros.com.dam.pmdm.dependiente.DependienteViewModel


@Composable
fun LoginDependiente(
    appViewModel: AppViewModel,
    loginViewModel: LoginDependienteViewModel,
    onLogin:() -> Unit,
    onExit: () -> Unit
) {
    //View models
    val dependienteViewModel = viewModel { DependienteViewModel() }

    val navController = rememberNavController()

    Button(onClick = {
        onLogin()
    }) {
        Text("Boton login")
    }


}