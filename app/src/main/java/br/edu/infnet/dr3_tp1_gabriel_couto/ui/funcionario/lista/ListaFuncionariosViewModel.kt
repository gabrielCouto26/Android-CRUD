package br.edu.infnet.dr3_tp1_gabriel_couto.ui.funcionario.lista

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.infnet.dr3_tp1_gabriel_couto.database.dao.FuncionarioDao
import br.edu.infnet.dr3_tp1_gabriel_couto.models.Funcionario
import kotlinx.coroutines.launch

class ListaFuncionariosViewModel(
        private val funcionarioDao: FuncionarioDao
) : ViewModel() {

    private val _funcionarios = MutableLiveData<List<Funcionario>>()
    val funcionarios: LiveData<List<Funcionario>>
        get() = _funcionarios

    fun getAll() {
        funcionarioDao.findAll()
                .addSnapshotListener { snapshot, error ->
                    if (error != null)
                        Log.i("ListaFuncSnapshotError", "${error.message}")
                    else
                        if (snapshot != null && !snapshot.isEmpty)
                            _funcionarios.value = snapshot.toObjects(Funcionario::class.java)
                }
    }
}