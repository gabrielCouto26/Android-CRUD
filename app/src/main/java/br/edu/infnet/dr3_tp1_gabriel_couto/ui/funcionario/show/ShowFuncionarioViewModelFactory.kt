package br.edu.infnet.dr3_tp1_gabriel_couto.ui.funcionario.show

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.infnet.dr3_tp1_gabriel_couto.database.dao.FuncionarioDao

class ShowFuncionarioViewModelFactory(
        private val funcionarioDao: FuncionarioDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ShowFuncionarioViewModel::class.java))
            return ShowFuncionarioViewModel(funcionarioDao) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}