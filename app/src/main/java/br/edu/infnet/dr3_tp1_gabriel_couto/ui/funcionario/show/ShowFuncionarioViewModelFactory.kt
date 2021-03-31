package br.edu.infnet.dr3_tp1_gabriel_couto.ui.funcionario.show

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.infnet.dr3_tp1_gabriel_couto.database.dao.FuncionarioDao
import br.edu.infnet.dr3_tp1_gabriel_couto.database.impl.FuncionarioDaoImpl
import br.edu.infnet.dr3_tp1_gabriel_couto.services.FirestorageService

class ShowFuncionarioViewModelFactory(
    private val firestorageService: FirestorageService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ShowFuncionarioViewModel::class.java))
            return ShowFuncionarioViewModel(firestorageService) as T
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}