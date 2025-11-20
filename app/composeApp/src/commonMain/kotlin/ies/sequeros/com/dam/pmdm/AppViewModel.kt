package ies.sequeros.com.dam.pmdm

import androidx.compose.material3.adaptive.WindowAdaptiveInfo

import androidx.lifecycle.ViewModel
import ies.sequeros.com.dam.pmdm.administrador.modelo.Dependiente
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel: ViewModel() {
    private val _darkMode = MutableStateFlow<Boolean>(false)
    val darkMode: StateFlow<Boolean> = _darkMode.asStateFlow()


    private val _windowsAdaptativeInfo =MutableStateFlow<WindowAdaptiveInfo?>(null);
    val windowsAdaptativeInfo=_windowsAdaptativeInfo.asStateFlow()
    fun setWindowsAdatativeInfo(wai: WindowAdaptiveInfo){
       _windowsAdaptativeInfo.value=null;
        _windowsAdaptativeInfo.value=wai;

    }


    fun setDarkMode(){
        _darkMode.value=true;
    }
    fun setLighMode(){
        _darkMode.value=false;
    }
    fun swithMode(){
        _darkMode.value=!_darkMode.value;
    }

}