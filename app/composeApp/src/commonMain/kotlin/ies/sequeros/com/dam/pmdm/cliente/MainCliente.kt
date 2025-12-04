package ies.sequeros.com.dam.pmdm.cliente

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ies.sequeros.com.dam.pmdm.AppViewModel
import ies.sequeros.com.dam.pmdm.cliente.login.LoginCliente
import ies.sequeros.com.dam.pmdm.cliente.login.LoginClienteViewModel
import ies.sequeros.com.dam.pmdm.cliente.login.menu.MenuCarta
import ies.sequeros.com.dam.pmdm.cliente.login.menu.MenuCartaViewModel
import ies.sequeros.com.dam.pmdm.dependiente.login.LoginDependienteViewModel

@Composable
fun MainCliente(
    appViewModel: AppViewModel,
    clienteViewModel: ClienteViewModel,
    onExit: () -> Unit
    ) {
    val loginClienteViewModel = viewModel { LoginClienteViewModel() }
    val menuCartaViewModel = viewModel { MenuCartaViewModel() }
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = ClienteRoutes.Login
    ) {
        composable(ClienteRoutes.MainCliente){
            PrincipalCliente()
        }

        composable(ClienteRoutes.Login) {
            LoginCliente(appViewModel, loginClienteViewModel, {
                navController.navigate(ClienteRoutes.Menu)
            })
        }

        composable(ClienteRoutes.Menu) {
            MenuCarta(menuCartaViewModel){

            }
        }
    }
}