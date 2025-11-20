package ies.sequeros.com.dam.pmdm.administrador.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FactCheck
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Icecream
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold


import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import ies.sequeros.com.dam.pmdm.AppViewModel
import ies.sequeros.com.dam.pmdm.administrador.AdministradorViewModel

import ies.sequeros.com.dam.pmdm.administrador.ui.dependientes.Dependientes
import ies.sequeros.com.dam.pmdm.administrador.ui.dependientes.DependientesViewModel
import ies.sequeros.com.dam.pmdm.administrador.ui.dependientes.form.DependienteForm


@Suppress("ViewModelConstructorInComposable")
@Composable
fun MainAdministrador(
    appViewModel: AppViewModel,
    mainViewModel: MainAdministradorViewModel,
    administradorViewModel: AdministradorViewModel,
    dependientesViewModel: DependientesViewModel,


    onExit: () -> Unit
) {
    val navController = rememberNavController()
    val options by mainViewModel.filteredItems.collectAsState() //

    val wai by appViewModel.windowsAdaptativeInfo.collectAsState();
    mainViewModel.setOptions(
        listOf(
            ItemOption(
                Icons.Default.Home, {
                    navController.navigate(AdminRoutes.Main) {
                        launchSingleTop = true
                        popUpTo(AdminRoutes.Main)
                    }
                },
                "Home", false
            ),
            ItemOption(
                Icons.Default.Person,
                {
                    navController.navigate(AdminRoutes.Dependientes) {
                        //
                        launchSingleTop = true
                        popUpTo(AdminRoutes.Dependientes)
                    }
                },
                "",
                true
            ),

            ItemOption(
                Icons.Default.Icecream,
                {
                    navController.navigate(AdminRoutes.Categorias) {
                        //
                        launchSingleTop = true
                        popUpTo(AdminRoutes.Main)
                    }
                },
                "Users  Admin",
                true
            ),
            ItemOption(
                Icons.Default.Fastfood,
                {
                    navController.navigate(AdminRoutes.Productos) {
                        //
                        launchSingleTop = true
                        popUpTo(AdminRoutes.Main)
                    }
                },
                "Users  Admin",
                true
            ),
            ItemOption(
                Icons.AutoMirrored.Filled.FactCheck,
                {
                    navController.navigate(AdminRoutes.Pedido) {
                        //
                        launchSingleTop = true
                        popUpTo(AdminRoutes.Main)
                    }
                },
                "Users  Admin",
                true
            ),

            ItemOption(
                Icons.Default.DarkMode,
                {
                    appViewModel.swithMode()


                },
                "Darkmode",
                true
            ),

            ItemOption(Icons.Default.Close, {

                onExit()
            }, "Close", false)
            )
    )

    //icono seleccionado
    //var selected by remember { mutableStateOf(items[0]) }

    val adaptiveInfo = currentWindowAdaptiveInfo()


    val navegador: @Composable () -> Unit = {
        NavHost(
            navController,
            startDestination = AdminRoutes.Main
        ) {
            composable(AdminRoutes.Main) {

                PrincipalAdministrador()
            }
            composable(AdminRoutes.Dependientes){
                Dependientes(mainViewModel,dependientesViewModel,{
                    dependientesViewModel.setSelectedDependiente(it)
                    navController.navigate(AdminRoutes.Dependiente) {
                        launchSingleTop = true

                    }
                })
            }
            composable (AdminRoutes.Dependiente){
                DependienteForm(
                    dependientesViewModel,{
                        navController.popBackStack()
                    },{
                        dependientesViewModel.save(it)
                        navController.popBackStack()
                    }
                )
            }

        }
    }



    if (wai?.windowSizeClass?.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
        Scaffold(
            bottomBar = {

                NavigationBar {
                    mainViewModel.filteredItems.collectAsState().value.forEach { item ->
                        // if(!item.admin || (item.admin && appViewModel.hasPermission()))
                        NavigationBarItem(

                            selected = true,
                            onClick = { item.action() },

                            icon = { Icon(item.icon, contentDescription = item.name) },

                            )
                    }
                }
            }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                navegador()
            }
        }
    } else {

        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(
                    Modifier.then(
                        if (wai?.windowSizeClass?.windowWidthSizeClass == WindowWidthSizeClass.COMPACT)
                            Modifier.width(128.dp)
                        else Modifier.width(128.dp)
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight()  // ocupa todo el alto del drawer
                            .padding(vertical = 16.dp),
                        verticalArrangement = Arrangement.Center,  // centra verticalmente
                        horizontalAlignment = Alignment.CenterHorizontally  // opcional: centra horizontalmente
                    ) {

                        Spacer(Modifier.height(16.dp))
                        options.forEach { item ->
                            //si se tienen permiso
                            // if(!item.admin || (item.admin && appViewModel.hasPermission()))
                            NavigationDrawerItem(
                                icon = {
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center,

                                        ) {
                                        Icon(
                                            item.icon,
                                            tint = MaterialTheme.colorScheme.primary,
                                            contentDescription = item.name
                                        )
                                    }
                                },
                                label = { appViewModel.windowsAdaptativeInfo.collectAsState().value?.windowSizeClass.toString() }, // sin texto
                                selected = false,
                                onClick = { item.action() },
                                modifier = Modifier
                                    .padding(vertical = 4.dp) // espaciado entre items

                            )
                        }
                    }
                }
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        // Add a fixed height constraint to prevent "Size out of range" error
                        .height(600.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    navegador()

                }
            }
        )
    }


}


