package br.edu.infnet.dr3_tp1_gabriel_couto.ui.funcionario.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.infnet.dr3_tp1_gabriel_couto.database.impl.FuncionarioDaoImpl
import br.edu.infnet.dr3_tp1_gabriel_couto.services.FirebaseAuthService
import br.edu.infnet.dr3_tp1_gabriel_couto.services.FirestorageService

class FormFuncionarioViewModelFactory(
    private val funcionarioDaoImpl: FuncionarioDaoImpl,
    private val firestorageService: FirestorageService,
    private val firebaseAuthService: FirebaseAuthService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FormFuncionarioViewModel::class.java))
            return FormFuncionarioViewModel(funcionarioDaoImpl, firestorageService, firebaseAuthService) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}