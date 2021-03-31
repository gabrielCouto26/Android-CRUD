package br.edu.infnet.dr3_tp1_gabriel_couto.ui.cadastro

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.edu.infnet.dr3_tp1_gabriel_couto.R
import br.edu.infnet.dr3_tp1_gabriel_couto.models.Funcionario
import br.edu.infnet.dr3_tp1_gabriel_couto.services.FirebaseAuthService
import br.edu.infnet.dr3_tp1_gabriel_couto.services.FirestoreService
import kotlinx.android.synthetic.main.cadastro_fragment.*

class CadastroFragment : Fragment() {

    private lateinit var cadastroViewModel: CadastroViewModel
    private lateinit var firestoreService: FirestoreService
    private lateinit var firebaseAuthService: FirebaseAuthService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val firestoreService = FirestoreService()
        firebaseAuthService = FirebaseAuthService()

        val cadastroViewModelFactory = CadastroViewModelFactory(firebaseAuthService, firestoreService)
        cadastroViewModel = ViewModelProvider(this, cadastroViewModelFactory).get(CadastroViewModel::class.java)

        cadastroViewModel.status.observe(viewLifecycleOwner, Observer {
            if(it)
                findNavController().popBackStack() // volta pro login
        })

        cadastroViewModel.msg.observe(viewLifecycleOwner, Observer {
            if(!it.isNullOrBlank())
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        return inflater.inflate(R.layout.cadastro_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCadastrar.setOnClickListener{
            val nome = txtNomeFuncionarioCadastro.text.toString()
            val funcao = txtFuncaoFuncionarioCadastro.text.toString()
            val empresa = txtNomeEmpresaCadastro.text.toString()
            val email = txtEmailFuncionarioCadastro.text.toString()
            val senha = txtSenhaFuncionarioCadastro.text.toString()

            val funcionario = Funcionario(nome, funcao, empresa, email)
            cadastroViewModel.cadastrar(funcionario, senha)
        }
    }

}