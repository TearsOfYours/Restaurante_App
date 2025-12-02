package ies.sequeros.com.dam.pmdm.administrador.ui.categorias

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.BorrarDependienteUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.activar.ActivarDependienteUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.actualizar.ActualizarDependienteUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.cambiarpermisos.CambiarPermisosUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.crear.CrearDependienteUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.ListarDependientesUseCase
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import kotlinx.coroutines.launch

class CategoriasViewModel(
    //private val categoriaRepositorio: ICategoriaRepositorio,
    val almacenDatos: AlmacenDatos
) : ViewModel()
{
    //Implementar todos los usos de casos de uso de Categorias
    //Conseguir los "items" dentro de categorias

//    init {
//        actualizarDependienteUseCase = ActualizarDependienteUseCase(dependienteRepositorio,almacenDatos)
//        borrarDependienteUseCase = BorrarDependienteUseCase(dependienteRepositorio,almacenDatos)
//        crearDependienteUseCase = CrearDependienteUseCase(dependienteRepositorio,almacenDatos)
//        listarDependientesUseCase = ListarDependientesUseCase(dependienteRepositorio,almacenDatos)
//        activarDependienteUseCase = ActivarDependienteUseCase(dependienteRepositorio,almacenDatos)
//        cambiarPermisosUseCase= CambiarPermisosUseCase(dependienteRepositorio,almacenDatos)
//        viewModelScope.launch {
//            var items = listarDependientesUseCase.invoke()
//            _items.value.clear()
//            _items.value.addAll(items)
//
//        }
//    }

}