package br.edu.infnet.dr3_tp1_gabriel_couto.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.infnet.dr3_tp1_gabriel_couto.database.dao.FuncionarioDao
import br.edu.infnet.dr3_tp1_gabriel_couto.database.impl.FuncionarioDaoImpl
import br.edu.infnet.dr3_tp1_gabriel_couto.services.FirebaseAuthService
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(
        private val firebaseAuthService: FirebaseAuthService
) : ViewModel() {

        private val _status = MutableLiveData<Boolean>()
        val status: LiveData<Boolean> = _status

        private val _msg = MutableLiveData<String>()
        val msg: LiveData<String> = _msg

        fun verificarUsuario(email: String, senha: String) {
                val task = firebaseAuthService.signIn(email, senha)
                task
                        .addOnSuccessListener {
                                _status.value = true
                                _msg.value = "Usuário válido."
                        }
                        .addOnFailureListener{
                                _msg.value = "Usuário não cadastrado"
                        }
        }

}