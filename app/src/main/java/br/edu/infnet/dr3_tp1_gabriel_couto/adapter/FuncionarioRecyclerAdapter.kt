package br.edu.infnet.dr3_tp1_gabriel_couto.adapter

import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.dr3_tp1_gabriel_couto.R
import br.edu.infnet.dr3_tp1_gabriel_couto.models.Funcionario
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.lista_funcionario_recycler.view.*

class FuncionarioRecyclerAdapter(
        private val listaFuncionario: List<Funcionario>,
        private val actionClick: (Funcionario) -> Unit
) : RecyclerView.Adapter<FuncionarioRecyclerAdapter.FuncionarioViewHolder>(){

    class FuncionarioViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txtNomeFuncionario = view.txtNomeFuncionario
        val txtNomeEmpresa = view.txtNomeEmpresa
        val imgFuncionario = view.imgFuncionario
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuncionarioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_funcionario_recycler, parent, false)
        return FuncionarioViewHolder(view)
    }

    override fun getItemCount(): Int = listaFuncionario.size

    override fun onBindViewHolder(holder: FuncionarioViewHolder, position: Int) {
        val funcionario = listaFuncionario.get(position)
        holder.txtNomeFuncionario.text = funcionario.nome
        holder.txtNomeEmpresa.text = funcionario.empresa
        downloadFotoFuncionario(funcionario.email!!, holder.imgFuncionario)
        holder.itemView.setOnClickListener{
            actionClick(funcionario)
        }
    }

    private fun downloadFotoFuncionario(emailFuncionario: String, imageView: ImageView){
        val storageReference = FirebaseStorage.getInstance().reference
        val fileReference = storageReference.child("imagens/$emailFuncionario.png")
        val task = fileReference.getBytes(1024*1024)
        task
            .addOnSuccessListener {
                val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                imageView.setImageBitmap(bitmap)
            }
            .addOnFailureListener{
                Log.i("FuncionarioRecyclerAdapter", "${it.message}")
            }

    }

}