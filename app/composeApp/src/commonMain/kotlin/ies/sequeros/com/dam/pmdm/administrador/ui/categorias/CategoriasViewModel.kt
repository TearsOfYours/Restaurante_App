package ies.sequeros.com.dam.pmdm.administrador.ui.categorias

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.BorrarCategoriaUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.activar.ActivarCategoriaCommand
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.activar.ActivarCategoriaUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.actualizar.ActualizarCategoriaCommand
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.actualizar.ActualizarCategoriaUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.crear.CrearCategoriaCommand
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.crear.CrearCategoriaUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.ListarCategoriasUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.CategoriaDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.activar.ActivarDependienteCommand
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.actualizar.ActualizarDependienteCommand
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.DependienteDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.Categoria
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.administrador.ui.categorias.form.CategoriaFormState
import ies.sequeros.com.dam.pmdm.administrador.ui.dependientes.form.DependienteFormState
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoriasViewModel(
    private val categoriaRepositorio: ICategoriaRepositorio,
    val almacenDatos: AlmacenDatos
) : ViewModel()
{

    private val borrarCategoriaUseCase: BorrarCategoriaUseCase
    private val crearCategoriaUseCase: CrearCategoriaUseCase
    
    private val listarCategoriasUseCase: ListarCategoriasUseCase

    private val actualizarCategoriaUseCase: ActualizarCategoriaUseCase

    private val activarCategoriaUseCase: ActivarCategoriaUseCase

    private val _items = MutableStateFlow<MutableList<CategoriaDTO>>(mutableListOf())
    val items: StateFlow<List<CategoriaDTO>> = _items.asStateFlow()


    private val _selected = MutableStateFlow<CategoriaDTO?>(null)
    val selected = _selected.asStateFlow()
    
    //Implementar todos los usos de casos de uso de Categorias
    //Conseguir los "items" dentro de categorias

    init {
        actualizarCategoriaUseCase = ActualizarCategoriaUseCase(categoriaRepositorio,almacenDatos)
        borrarCategoriaUseCase = BorrarCategoriaUseCase(categoriaRepositorio,almacenDatos)
        crearCategoriaUseCase = CrearCategoriaUseCase(categoriaRepositorio,almacenDatos)
        listarCategoriasUseCase = ListarCategoriasUseCase(categoriaRepositorio,almacenDatos)
        activarCategoriaUseCase = ActivarCategoriaUseCase(categoriaRepositorio,almacenDatos)
        viewModelScope.launch {
            var items = listarCategoriasUseCase.invoke()
            _items.value.clear()
            _items.value.addAll(items)

        }
    }
    fun setSelectedCategoria(item: CategoriaDTO?) {
        _selected.value = item
    }

    fun add(formSate: CategoriaFormState) {
        val command = CrearCategoriaCommand(
            formSate.nombre,
            formSate.imagePath

        )
        viewModelScope.launch {
            try {
                val categoria = crearCategoriaUseCase.invoke(command)
                _items.value = (_items.value + categoria) as MutableList<CategoriaDTO>
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun update(formState: CategoriaFormState) {
        val command = ActualizarCategoriaCommand(
            formState.nombre,
            selected.value!!.id!!,
            formState.imagePath
        )
        viewModelScope.launch {
            val item = actualizarCategoriaUseCase.invoke(command)
            _items.update { current ->
                current.map { if (it.id == item.id) item else it } as MutableList<CategoriaDTO>
            }
        }
    }

    fun switchEnableCategoria(item: CategoriaDTO) {
        val command= ActivarCategoriaCommand(
            item.id,
            !item.enabled,
        )

        viewModelScope.launch {
            val item=activarCategoriaUseCase.invoke(command)

            _items.value = _items.value.map {
                if (item.id == it.id)
                    item
                else
                    it
            } as MutableList<CategoriaDTO>
        }

    }


    fun delete(item: CategoriaDTO) {
        viewModelScope.launch {
            borrarCategoriaUseCase.invoke(item.id)
            _items.update { current ->
                current.filterNot { it.id == item.id }.toMutableList()
            }
        }
    }
    fun save(item: CategoriaFormState) {
        if (_selected.value == null)
            this.add(item)
        /*else
            this.update(item)*/
    }

}