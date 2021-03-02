package br.edu.infnet.dr3_tp1_gabriel_couto.ui.cadastro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.infnet.dr3_tp1_gabriel_couto.services.FirebaseAuthService
import br.edu.infnet.dr3_tp1_gabriel_couto.services.FirestoreService

class CadastroViewModelFactory(
    private val firebaseAuthService: FirebaseAuthService,
    private val firestoreService: FirestoreService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CadastroViewModel::class.java))
            return CadastroViewModel(firebaseAuthService, firestoreService) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}