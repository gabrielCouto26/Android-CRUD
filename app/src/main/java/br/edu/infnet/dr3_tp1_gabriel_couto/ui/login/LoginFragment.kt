package br.edu.infnet.dr3_tp1_gabriel_couto.ui.login
/*

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import br.edu.infnet.dr3_tp1_gabriel_couto.R
import br.edu.infnet.dr3_tp1_gabriel_couto.database.dao.FuncionarioDaoImpl
import br.edu.infnet.dr3_tp1_gabriel_couto.ui.cadastro.CadastroViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)
        val loginViewModelFactory = LoginViewModelFactory(FuncionarioDaoImpl())

        loginViewModel = ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnEntrar.setOnClickListener {
            val email = emailInput.text.toString()
            val senha = senhaInput.text.toString()

            if (email != "" && senha != "") {
                //it.findNavController().navigate(R.id.action_loginFragment_to_listaProdutoresFragment)
            } else {
                Snackbar.make(
                    root_login_layout,
                    "Email ou senha inválido",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        btnRedirectCadastro.setOnClickListener{
            //it.findNavController().navigate(R.id.action_loginFragment_to_cadastroFragment2)
        }
    }

}*/