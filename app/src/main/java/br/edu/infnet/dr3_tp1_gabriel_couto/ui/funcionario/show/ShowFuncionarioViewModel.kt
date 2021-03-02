package br.edu.infnet.dr3_tp1_gabriel_couto.ui.funcionario.show

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.infnet.dr3_tp1_gabriel_couto.database.dao.FuncionarioDao
import br.edu.infnet.dr3_tp1_gabriel_couto.services.FirestorageService
import com.google.firebase.storage.FirebaseStorage
import java.io.File


class ShowFuncionarioViewModel(
    application: Application,
    private val funcionarioDao: FuncionarioDao,
    private val firestorageService: FirestorageService
) : AndroidViewModel(application) {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    private val _fotoFuncionario = MutableLiveData<Bitmap>()
    val fotoFuncionario: LiveData<Bitmap> = _fotoFuncionario

    fun setUpFotoFuncionario(email: String){
        val fileReferennceBytes = firestorageService.getFileBytes(email)
        fileReferennceBytes
            .addOnSuccessListener {
                val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                _fotoFuncionario.value = bitmap
            }.addOnFailureListener{
                Log.i("StorageGetBytes", "${it.message}")
            }
    }

    fun setFotoFuncionario(bitmap: Bitmap){
        _fotoFuncionario.value = bitmap
    }

    /*fun downloadFotoFuncionario(emailFuncionario: String){
        val file = File.createTempFile("funcionario", ".png")
        val task = firestorageService.downloadFotoFuncionario(emailFuncionario, file)
        task
            .addOnSuccessListener {
                _fotoFuncionario.value = file.toUri()
            }
            .addOnFailureListener{
                _msg.value = "Falhou: ${it.message}"
            }
    }*/


}