package br.edu.infnet.dr3_tp1_gabriel_couto.ui.funcionario.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.infnet.dr3_tp1_gabriel_couto.database.dao.FuncionarioDao
import br.edu.infnet.dr3_tp1_gabriel_couto.services.FirebaseAuthService
import br.edu.infnet.dr3_tp1_gabriel_couto.services.FirestorageService
import br.edu.infnet.dr3_tp1_gabriel_couto.services.FirestoreService

class FormFuncionarioViewModelFactory(
    private val funcionarioDao: FuncionarioDao,
    private val application: Application,
    private val firestorageService: FirestorageService,
    private val firebaseAuthService: FirebaseAuthService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FormFuncionarioViewModel::class.java))
            return FormFuncionarioViewModel(funcionarioDao, application, firestorageService, firebaseAuthService) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}