package br.edu.infnet.dr3_tp1_gabriel_couto.ui.cadastro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.infnet.dr3_tp1_gabriel_couto.database.impl.FuncionarioDaoImpl
import br.edu.infnet.dr3_tp1_gabriel_couto.models.Funcionario
import br.edu.infnet.dr3_tp1_gabriel_couto.services.FirebaseAuthService
import kotlinx.coroutines.launch

class CadastroViewModel(
    private val firebaseAuthService: FirebaseAuthService,
    private val funcionarioDaoImpl: FuncionarioDaoImpl
) : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun cadastrar(funcionario: Funcionario, senha: String) {
        viewModelScope.launch {
            val taskAuth = firebaseAuthService.createUserWithEmailAndPassword(funcionario.email!!, senha)
            val taskFirestore = funcionarioDaoImpl.insertOrUpdate(funcionario)

            taskAuth
                .addOnSuccessListener {
                    _status.value = true
                    _msg.value = "Cadastro no FirebaseAuth realizado com sucesso."
                }
                .addOnFailureListener {
                    _msg.value = "${it.message}"
                }

            taskFirestore
                .addOnSuccessListener {
                    _status.value = true
                    _msg.value = "Cadastro no Firestore realizado com sucesso."
                }
                .addOnFailureListener {
                    _msg.value = "${it.message}"
                }
        }

    }

}