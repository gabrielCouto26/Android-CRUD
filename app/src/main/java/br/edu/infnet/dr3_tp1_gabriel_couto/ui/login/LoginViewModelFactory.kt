package br.edu.infnet.dr3_tp1_gabriel_couto.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.infnet.dr3_tp1_gabriel_couto.database.dao.FuncionarioDao
import br.edu.infnet.dr3_tp1_gabriel_couto.services.FirebaseAuthService

class LoginViewModelFactory(
        private val funcionarioDao: FuncionarioDao,
        private val firebaseAuthService: FirebaseAuthService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java))
            return LoginViewModel(funcionarioDao, firebaseAuthService) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}