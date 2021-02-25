package br.edu.infnet.dr3_tp1_gabriel_couto.ui.funcionario.lista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.infnet.dr3_tp1_gabriel_couto.database.dao.FuncionarioDao

class ListaFuncionariosViewModelFactory(
        private val funcionarioDao: FuncionarioDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListaFuncionariosViewModel::class.java))
            return ListaFuncionariosViewModel(funcionarioDao) as T
        throw IllegalArgumentException("Classe ViewModel deve ser ListaFuncionariosViewModel.")
    }
}