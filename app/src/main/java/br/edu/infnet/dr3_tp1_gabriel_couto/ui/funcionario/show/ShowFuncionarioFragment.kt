package br.edu.infnet.dr3_tp1_gabriel_couto.ui.funcionario.show

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.edu.infnet.dr3_tp1_gabriel_couto.R
import br.edu.infnet.dr3_tp1_gabriel_couto.database.impl.FuncionarioDaoImpl
import br.edu.infnet.dr3_tp1_gabriel_couto.models.Funcionario
import br.edu.infnet.dr3_tp1_gabriel_couto.models.FuncionarioUtil
import br.edu.infnet.dr3_tp1_gabriel_couto.services.FirestorageService
import br.edu.infnet.dr3_tp1_gabriel_couto.services.FirestoreService
import kotlinx.android.synthetic.main.show_funcionario_fragment.*

class ShowFuncionarioFragment : Fragment() {

    private lateinit var viewModel: ShowFuncionarioViewModel
    private lateinit var firestorageService: FirestorageService


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firestorageService = FirestorageService()

        val view = inflater.inflate(R.layout.show_funcionario_fragment, container, false)

        val funcionarioViewModelFactory = ShowFuncionarioViewModelFactory(firestorageService)
        viewModel = ViewModelProvider(this, funcionarioViewModelFactory).get(ShowFuncionarioViewModel::class.java)

        viewModel.fotoFuncionario.observe(viewLifecycleOwner){
            if(it != null)
                imgFuncionarioPerfilFuncionario.setImageURI(it)

        }
        viewModel.status.observe(viewLifecycleOwner){ status ->
            if(status)
                findNavController().popBackStack()
        }

        viewModel.msg.observe(viewLifecycleOwner){ msg ->
            if(!msg.isNullOrBlank())
                Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
        }

        viewModel.cep.observe(viewLifecycleOwner){
            if(it != null)
                txtCepFuncionarioPerfilFuncionario.setText(it.toString())

        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val funcionarioUtil = FuncionarioUtil.funcionarioSelecionado

        if(funcionarioUtil != null){
            preencherFormulario(funcionarioUtil)

            if(funcionarioUtil.cep != null)
                viewModel.buscaCep(funcionarioUtil.cep.toString())
        }

    }

    private fun preencherFormulario(funcionario: Funcionario) {
        try{
            txtNomeFuncionarioPerfilFuncionario.setText(funcionario.nome)
            txtFuncaoFuncionarioPerfilFuncionario.setText(funcionario.funcao)
            txtNomeEmpresaPerfilFuncionario.setText(funcionario.empresa)
            txtEmailFuncionarioPerfilFuncionario.setText(funcionario.email)
            txtCepFuncionarioPerfilFuncionario.setText(funcionario.cep)

            if(funcionario.foto!!)
                viewModel.downloadFotoFuncionario(funcionario.email!!)

        } catch (e: Error){
            Log.e("preencherFormulario", "${e.message}")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (resultCode == Activity.RESULT_OK){
                val foto: Uri = data!!.data!!
                viewModel.setFotoFuncionario(foto)
            }
        } catch (e: Error) {
            Log.e("ShowFuncFragment", "${e.message}")
        }
    }

}