package br.edu.infnet.dr3_tp1_gabriel_couto.ui.funcionario.lista

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.infnet.dr3_tp1_gabriel_couto.R
import br.edu.infnet.dr3_tp1_gabriel_couto.adapter.FuncionarioRecyclerAdapter
import br.edu.infnet.dr3_tp1_gabriel_couto.database.dao.FuncionarioDaoImpl
import br.edu.infnet.dr3_tp1_gabriel_couto.models.Funcionario
import br.edu.infnet.dr3_tp1_gabriel_couto.models.FuncionarioUtil
import kotlinx.android.synthetic.main.lista_funcionario_recycler.*
import kotlinx.android.synthetic.main.lista_funcionarios_fragment.*

class ListaFuncionariosFragment : Fragment() {

    private lateinit var listaFuncionariosViewModel: ListaFuncionariosViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.lista_funcionarios_fragment, container, false)

        val listaFuncionarioVMF = ListaFuncionariosViewModelFactory(FuncionarioDaoImpl())

        listaFuncionariosViewModel = ViewModelProvider(this, listaFuncionarioVMF).get(ListaFuncionariosViewModel::class.java)

        listaFuncionariosViewModel.getAll()

        listaFuncionariosViewModel
                .funcionarios
                .observe(viewLifecycleOwner){
                    setupListViewFuncionarios(it)
                }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabNovoFuncionario.setOnClickListener {
            findNavController().navigate(R.id.action_listaFuncionariosFragment_to_formFuncionarioFragment)
        }
    }

    private fun setupListViewFuncionarios(funcionarios: List<Funcionario>) {
        listaFuncionarios.adapter = FuncionarioRecyclerAdapter(funcionarios) {
            FuncionarioUtil.funcionarioSelecionado = it
            findNavController().navigate(R.id.formFuncionarioFragment)
        }
        listaFuncionarios.layoutManager = LinearLayoutManager(requireContext())
    }

}