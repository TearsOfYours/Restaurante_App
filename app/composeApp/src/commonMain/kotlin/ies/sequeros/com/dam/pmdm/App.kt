package ies.sequeros.com.dam.pmdm

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ies.sequeros.com.dam.pmdm.administrador.AdministradorViewModel
import ies.sequeros.com.dam.pmdm.administrador.infraestructura.ficheros.FileCategoriaRepository
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio

import ies.sequeros.com.dam.pmdm.administrador.ui.MainAdministrador
import ies.sequeros.com.dam.pmdm.administrador.ui.MainAdministradorViewModel
import ies.sequeros.com.dam.pmdm.administrador.ui.categorias.CategoriasViewModel
import ies.sequeros.com.dam.pmdm.administrador.ui.dependientes.DependientesViewModel
import ies.sequeros.com.dam.pmdm.administrador.ui.listado.PedidosViewModel
import ies.sequeros.com.dam.pmdm.administrador.ui.productos.ProductoViewModel
import ies.sequeros.com.dam.pmdm.cliente.ClienteViewModel
import ies.sequeros.com.dam.pmdm.cliente.MainCliente
import ies.sequeros.com.dam.pmdm.dependiente.DependienteRoutes
import ies.sequeros.com.dam.pmdm.dependiente.login.LoginDependiente
import ies.sequeros.com.dam.pmdm.dependiente.login.LoginDependienteViewModel
import ies.sequeros.com.dam.pmdm.dependiente.DependienteViewModel
import ies.sequeros.com.dam.pmdm.dependiente.MainDependiente

@Suppress("ViewModelConstructorInComposable")
@Composable

fun App(productoRepositorio: IProductoRepositorio, categoriaRepositorio: ICategoriaRepositorio, dependienteRepositorio : IDependienteRepositorio,almacenImagenes:AlmacenDatos) {

    //view model<<
    val appViewModel= viewModel {  AppViewModel() }
    val mainViewModel= remember { MainAdministradorViewModel() }
    val administradorViewModel= viewModel { AdministradorViewModel() }
    val mainDependienteViewModel = viewModel { DependienteViewModel() }
    val clienteViewModel = viewModel { ClienteViewModel() }
    val loginDependienteViewModel = viewModel { LoginDependienteViewModel() }
    val dependientesViewModel = viewModel{ DependientesViewModel(
        dependienteRepositorio, almacenImagenes
    )}
    val categoriasViewModel = viewModel { CategoriasViewModel(categoriaRepositorio, almacenImagenes) }
    val productosViewModel = viewModel { ProductoViewModel(productoRepositorio,categoriaRepositorio, almacenImagenes) }
    val pedidosViewModel = viewModel { PedidosViewModel() }

    appViewModel.setWindowsAdatativeInfo( currentWindowAdaptiveInfo())
    val navController= rememberNavController()
//para cambiar el modo
    AppTheme(appViewModel.darkMode.collectAsState()) {

        NavHost(
            navController,
            startDestination = AppRoutes.Main
        ) {
            //Por algún motivo TPV y dependiente funcionan al reves
            composable(AppRoutes.Main) {
                Principal({
                    navController.navigate(AppRoutes.Administrador)
                },{
                    navController.navigate(AppRoutes.Dependiente)
                },{
                    navController.navigate(AppRoutes.Cliente)
                })
            }
            composable (AppRoutes.Administrador){
                MainAdministrador(appViewModel,mainViewModel,administradorViewModel,
                    dependientesViewModel,categoriasViewModel, productosViewModel, pedidosViewModel

                ) { navController.popBackStack() }
            }
            composable(AppRoutes.Cliente) {
                MainCliente(appViewModel, clienteViewModel) {}
            }
            composable(AppRoutes.Dependiente) {
                MainDependiente(appViewModel, mainDependienteViewModel) {}
            }

        }
    }

}