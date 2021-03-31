package br.edu.infnet.dr3_tp1_gabriel_couto.ui.funcionario.lista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.infnet.dr3_tp1_gabriel_couto.database.impl.FuncionarioDaoImpl

class ListaFuncionariosViewModelFactory(
        private val funcionarioDaoImpl: FuncionarioDaoImpl
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListaFuncionariosViewModel::class.java))
            return ListaFuncionariosViewModel(funcionarioDaoImpl) as T
        throw IllegalArgumentException("Classe ViewModel deve ser ListaFuncionariosViewModel.")
    }
}