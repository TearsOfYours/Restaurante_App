package ies.sequeros.com.dam.pmdm.dependiente

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ies.sequeros.com.dam.pmdm.AppViewModel
import ies.sequeros.com.dam.pmdm.dependiente.login.LoginDependiente
import ies.sequeros.com.dam.pmdm.dependiente.login.LoginDependienteViewModel
import ies.sequeros.com.dam.pmdm.dependiente.login.pedidos.PedidosDependiente
import ies.sequeros.com.dam.pmdm.dependiente.login.pedidos.PedidosDependienteViewModel

@Composable
fun MainDependiente(
    appViewModel: AppViewModel,
    dependienteViewModel: DependienteViewModel,
    onExit: () -> Unit
){
    val loginDependienteViewModel = viewModel { LoginDependienteViewModel() }
    val pedidosDependienteViewModel = viewModel { PedidosDependienteViewModel() }
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = DependienteRoutes.Login
    ){
        composable(DependienteRoutes.Login) {
            LoginDependiente(appViewModel, loginDependienteViewModel, {
               navController.navigate(DependienteRoutes.Pedidos)
            }, {})
        }
        composable(DependienteRoutes.Pedidos) {
            PedidosDependiente(pedidosDependienteViewModel) {

            }
        }
    }

}