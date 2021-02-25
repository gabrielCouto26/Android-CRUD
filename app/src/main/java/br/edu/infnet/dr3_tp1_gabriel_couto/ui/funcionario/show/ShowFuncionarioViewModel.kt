package br.edu.infnet.dr3_tp1_gabriel_couto.ui.funcionario.show

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.infnet.dr3_tp1_gabriel_couto.database.dao.FuncionarioDao
import com.google.firebase.storage.FirebaseStorage


class ShowFuncionarioViewModel(
    private val funcionarioDao: FuncionarioDao
) : ViewModel() {
    private val _fotoFuncionario = MutableLiveData<Bitmap?>()
    val fotoFuncionario: LiveData<Bitmap?> = _fotoFuncionario

    init {
        _fotoFuncionario.value = null
    }

    fun deleteFuncionario(emailFuncionario: String){
        funcionarioDao.delete(emailFuncionario)
    }

    fun setUpFotoFuncionario(){
        val fbStorageReference = FirebaseStorage.getInstance().reference
        val fileReference = fbStorageReference.child("imagens/gabriel@email.com.jpeg")

        fileReference
            .getBytes(1024 * 1024)
            .addOnSuccessListener {
                val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                _fotoFuncionario.value = bitmap
            }.addOnFailureListener{
                Log.i("StorageGetBytes", "${it.message}")
            }
    }

}