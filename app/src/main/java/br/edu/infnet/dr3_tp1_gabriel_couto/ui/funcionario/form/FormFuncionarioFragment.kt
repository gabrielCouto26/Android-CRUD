package br.edu.infnet.dr3_tp1_gabriel_couto.ui.funcionario.form

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import br.edu.infnet.dr3_tp1_gabriel_couto.R
import br.edu.infnet.dr3_tp1_gabriel_couto.database.dao.FuncionarioDaoImpl
import br.edu.infnet.dr3_tp1_gabriel_couto.models.Funcionario
import br.edu.infnet.dr3_tp1_gabriel_couto.models.FuncionarioUtil
import kotlinx.android.synthetic.main.form_funcionario_fragment.*
import kotlinx.android.synthetic.main.show_funcionario_fragment.*

class FormFuncionarioFragment : Fragment() {

    private lateinit var formFuncionarioViewModel: FormFuncionarioViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.form_funcionario_fragment, container, false)
        val application = requireActivity().application
        val cadastroViewModelFactory = FormFuncionarioViewModelFactory(FuncionarioDaoImpl(), application)

        formFuncionarioViewModel = ViewModelProvider(this, cadastroViewModelFactory).get(FormFuncionarioViewModel::class.java)

        formFuncionarioViewModel.status.observe(viewLifecycleOwner){ status ->
            if(status)
                findNavController().popBackStack()
        }

        formFuncionarioViewModel.msg.observe(viewLifecycleOwner){ msg ->
            if(!msg.isNullOrBlank())
                Toast.makeText(
                        requireContext(),
                        msg,
                        Toast.LENGTH_LONG
                ).show()
        }

        formFuncionarioViewModel.fotoFuncionario.observe(viewLifecycleOwner){ uri ->
            if(uri != null)
                imgCadastroFuncionario.setImageURI(uri)

        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(FuncionarioUtil.funcionarioSelecionado != null)
            preencherFormulario(FuncionarioUtil.funcionarioSelecionado!!)

        btnCadastrar.setOnClickListener{
            val nome = inputFuncionarioNome.text.toString()
            val funcao = inputFuncionarioFuncao.text.toString()
            val empresa = inputFuncionarioEmpresa.text.toString()
            val email = inputFuncionarioEmail.text.toString()
            formFuncionarioViewModel.store(nome, funcao, empresa, email)
        }

        imgCadastroFuncionario.setOnClickListener{
            selecionarImagem()
        }

        btnExcluirFuncionario.setOnClickListener{
            val emailFuncionario = inputFuncionarioEmail.text.toString()
            formFuncionarioViewModel.deleteFuncionario(emailFuncionario)
            FuncionarioUtil.funcionarioSelecionado = null
            findNavController().navigate(R.id.listaFuncionariosFragment)
            // falta excluir foto do storage
        }
    }

    private fun preencherFormulario(funcinario: Funcionario) {
        inputFuncionarioNome.setText(funcinario.nome)
        inputFuncionarioFuncao.setText(funcinario.funcao)
        inputFuncionarioEmpresa.setText(funcinario.empresa)
        inputFuncionarioEmail.setText(funcinario.email)
        btnCadastrar.text = "Atualizar"
        formFuncionarioViewModel.downloadFotoFuncionario(funcinario.email!!) // IMPLEMENTAR VERIFICAÇAO IF NOT NULL
    }

    private fun selecionarImagem(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            val foto: Uri = data!!.data!!
            formFuncionarioViewModel.setFotoFuncionario(foto)
        }
    }

}