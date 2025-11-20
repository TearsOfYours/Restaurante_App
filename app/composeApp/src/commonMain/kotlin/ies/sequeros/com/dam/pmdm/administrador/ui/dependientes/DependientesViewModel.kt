package ies.sequeros.com.dam.pmdm.administrador.ui.dependientes

import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.BorrarDependienteUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.activar.ActivarDependienteCommand
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.activar.ActivarDependienteUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.actualizar.ActualizarDependienteCommand
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.actualizar.ActualizarDependienteUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.cambiaclave.CambiarClaveDependienteUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.cambiarpermisos.CambiarPermisosCommand
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.cambiarpermisos.CambiarPermisosUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.crear.CrearDependienteCommand
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.crear.CrearDependienteUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.DependienteDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.ListarDependientesUseCase
import ies.sequeros.com.dam.pmdm.administrador.infraestructura.memoria.FileDependienteRepository
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.administrador.modelo.Dependiente
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio
import ies.sequeros.com.dam.pmdm.administrador.ui.MainAdministradorViewModel
import ies.sequeros.com.dam.pmdm.administrador.ui.dependientes.form.DependienteFormState

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DependientesViewModel(
    //private val administradorViewModel: MainAdministradorViewModel,
    private val dependienteRepositorio: IDependienteRepositorio,
     val almacenDatos: AlmacenDatos
) : ViewModel() {
    //los casos de uso se crean dentro para la recomposición
    //se pueden injectar también, se tratará en próximos temas
    private val borrarDependienteUseCase: BorrarDependienteUseCase
    private val crearDependienteUseCase: CrearDependienteUseCase
    private val listarDependientesUseCase: ListarDependientesUseCase

    private val actualizarDependienteUseCase: ActualizarDependienteUseCase
    private val activarDependienteUseCase: ActivarDependienteUseCase
    private val cambiarPermisosUseCase:  CambiarPermisosUseCase

    private val _items = MutableStateFlow<MutableList<DependienteDTO>>(mutableListOf())
    val items: StateFlow<List<DependienteDTO>> = _items.asStateFlow()
    private val _selected = MutableStateFlow<DependienteDTO?>(null)
    val selected = _selected.asStateFlow()

    init {
        actualizarDependienteUseCase = ActualizarDependienteUseCase(dependienteRepositorio,almacenDatos)
        borrarDependienteUseCase = BorrarDependienteUseCase(dependienteRepositorio,almacenDatos)
        crearDependienteUseCase = CrearDependienteUseCase(dependienteRepositorio,almacenDatos)
        listarDependientesUseCase = ListarDependientesUseCase(dependienteRepositorio,almacenDatos)
        activarDependienteUseCase = ActivarDependienteUseCase(dependienteRepositorio,almacenDatos)
        cambiarPermisosUseCase= CambiarPermisosUseCase(dependienteRepositorio,almacenDatos)
        viewModelScope.launch {
            var items = listarDependientesUseCase.invoke()
            _items.value.clear()
            _items.value.addAll(items)

        }
    }

    fun setSelectedDependiente(item: DependienteDTO?) {
        _selected.value = item
    }

    fun switchEnableDependiente(item: DependienteDTO) {
        val command= ActivarDependienteCommand(
            item.id,
            item.enabled,
        )

        viewModelScope.launch {
            val item=activarDependienteUseCase.invoke(command)

            _items.value = _items.value.map {
                if (item.id == it.id)
                    item
                else
                    it
            } as MutableList<DependienteDTO>
        }

    }

    fun switchAdmin(item: DependienteDTO) {
        val command= CambiarPermisosCommand(
            item.id,
            item.isAdmin,
        )

        viewModelScope.launch {
            val item=cambiarPermisosUseCase.invoke(command)

            _items.value = _items.value.map {
                if (item.id == it.id)
                    item
                else
                    it
            }.toMutableList()
        }
    }

    fun delete(item: DependienteDTO) {
        viewModelScope.launch {
          //  borrarDependienteUseCase.invoke(item.id)
            _items.update { current ->
                current.filterNot { it.id == item.id }.toMutableList()
            }
        }

    }

    fun add(formState: DependienteFormState) {
        val command = CrearDependienteCommand(
            formState.nombre,
            formState.email,
            formState.password,
            formState.imagePath,
            formState.enabled,
            formState.isadmin
        )
        viewModelScope.launch {
            try {
                val user = crearDependienteUseCase.invoke(command)
                _items.value = (_items.value + user) as MutableList<DependienteDTO>
            }catch (e:Exception){
                throw  e
            }

        }
    }

    fun update(formState: DependienteFormState) {
        val command = ActualizarDependienteCommand(
            selected.value!!.id!!,
            formState.nombre,
            formState.email,
            formState.imagePath,
            formState.enabled,
            formState.isadmin
        )
        viewModelScope.launch {
            val item = actualizarDependienteUseCase.invoke(command)
            _items.update { current ->
                current.map { if (it.id == item.id) item else it } as MutableList<DependienteDTO>
            }
        }


    }

    fun save(item: DependienteFormState) {
        if (_selected.value == null)
            this.add(item)
        else
            this.update(item)
    }



}