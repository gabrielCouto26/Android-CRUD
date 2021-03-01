package br.edu.infnet.dr3_tp1_gabriel_couto.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.infnet.dr3_tp1_gabriel_couto.database.dao.FuncionarioDao

class LoginViewModelFactory(
        private val funcionarioDao: FuncionarioDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java))
            return LoginViewModel(funcionarioDao) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}