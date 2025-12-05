package ies.sequeros.com.dam.pmdm.cliente

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ies.sequeros.com.dam.pmdm.AppViewModel
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.ui.AdminRoutes
import ies.sequeros.com.dam.pmdm.cliente.login.LoginCliente
import ies.sequeros.com.dam.pmdm.cliente.login.LoginClienteViewModel
import ies.sequeros.com.dam.pmdm.cliente.login.menu.MenuCarta
import ies.sequeros.com.dam.pmdm.cliente.login.menu.MenuCartaViewModel
import ies.sequeros.com.dam.pmdm.cliente.login.menu.pedido.TerminarPedido
import ies.sequeros.com.dam.pmdm.cliente.login.menu.pedido.TerminarPedidoViewModel
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos

@Composable
fun MainCliente(
    appViewModel: AppViewModel,
    categoriaRepositorio: ICategoriaRepositorio,
    pedidoRepositorio: IPedidoRepositorio,
    productoRepositorio: IProductoRepositorio,
    almacenDatos: AlmacenDatos,
    onExit: () -> Unit
) {
    val loginClienteViewModel = viewModel { LoginClienteViewModel() }
    val menuCartaViewModel = viewModel { MenuCartaViewModel() }
    val terminarPedidoViewModel = viewModel { TerminarPedidoViewModel(productoRepositorio, categoriaRepositorio, pedidoRepositorio, almacenDatos) }
    var nombre by remember { mutableStateOf("") }
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = ClienteRoutes.Login
    ) {

        composable(ClienteRoutes.Login) {
            LoginCliente(appViewModel, loginClienteViewModel, { nameLogin ->
                nombre = nameLogin
                navController.navigate(ClienteRoutes.Menu)
            }, {
                navController.popBackStack()
            })

        }

        composable(ClienteRoutes.Menu) {
            MenuCarta(
                menuCartaViewModel, categoriaRepositorio, productoRepositorio, almacenDatos, nombre,
                {
                    navController.navigate(ClienteRoutes.Login) {
                        onExit()
                    }
                },
                { navController.navigate(ClienteRoutes.Pedido) })
        }

        composable(ClienteRoutes.Pedido) {
            TerminarPedido(terminarPedidoViewModel)
        }

    }

}
