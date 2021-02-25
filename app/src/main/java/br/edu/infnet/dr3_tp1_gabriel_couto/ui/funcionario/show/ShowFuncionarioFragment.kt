package br.edu.infnet.dr3_tp1_gabriel_couto.ui.funcionario.show

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import br.edu.infnet.dr3_tp1_gabriel_couto.R
import br.edu.infnet.dr3_tp1_gabriel_couto.database.dao.FuncionarioDaoImpl
import kotlinx.android.synthetic.main.show_funcionario_fragment.*

class ShowFuncionarioFragment : Fragment() {

    private lateinit var showFuncionarioViewModel: ShowFuncionarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.show_funcionario_fragment, container, false)

        val funcionarioViewModelFactory = ShowFuncionarioViewModelFactory(FuncionarioDaoImpl())
        showFuncionarioViewModel = ViewModelProvider(this, funcionarioViewModelFactory).get(ShowFuncionarioViewModel::class.java)
        showFuncionarioViewModel.fotoFuncionario.observe(viewLifecycleOwner){ bitmap ->
            if(bitmap != null)
                imgFuncionarioPerfilFuncionario.setImageBitmap(bitmap)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showFuncionarioViewModel.setUpFotoFuncionario()
    }

}