package br.edu.infnet.dr3_tp1_gabriel_couto.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import br.edu.infnet.dr3_tp1_gabriel_couto.R
import br.edu.infnet.dr3_tp1_gabriel_couto.database.dao.FuncionarioDaoImpl
import br.edu.infnet.dr3_tp1_gabriel_couto.services.FirebaseAuthService
import br.edu.infnet.dr3_tp1_gabriel_couto.services.FirestoreService
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var firestoreService: FirestoreService
    private lateinit var firebaseAuthService: FirebaseAuthService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firestoreService = FirestoreService()
        firebaseAuthService = FirebaseAuthService()

        val loginViewModelFactory = LoginViewModelFactory(FuncionarioDaoImpl(firestoreService), firebaseAuthService)
        loginViewModel = ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)

        loginViewModel.status.observe(viewLifecycleOwner, Observer {
            if(it)
                findNavController().navigate(R.id.listaFuncionariosFragment)
        })

        loginViewModel.msg.observe(viewLifecycleOwner, Observer {
            if(!it.isNullOrBlank())
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnEntrar.setOnClickListener {

            val email = emailInput.text.toString()
            val senha = senhaInput.text.toString()

            if (email.isBlank() && senha.isBlank()) {
                loginViewModel.verificarUsuario(email, senha)
            } else {
                Toast.makeText(requireContext(), "Email ou senha inválidos", Toast.LENGTH_LONG).show()
            }
        }

        btnRedirectCadastro.setOnClickListener{
            it.findNavController().navigate(R.id.cadastroFragment)
        }
    }

}