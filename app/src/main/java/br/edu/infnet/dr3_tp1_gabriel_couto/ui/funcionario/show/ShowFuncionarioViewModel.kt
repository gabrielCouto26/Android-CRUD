package br.edu.infnet.dr3_tp1_gabriel_couto.ui.funcionario.show

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.*
import br.edu.infnet.dr3_tp1_gabriel_couto.database.dao.FuncionarioDao
import br.edu.infnet.dr3_tp1_gabriel_couto.models.FuncionarioUtil
import br.edu.infnet.dr3_tp1_gabriel_couto.models.api.Cep
import br.edu.infnet.dr3_tp1_gabriel_couto.services.FirestorageService
import br.edu.infnet.dr3_tp1_gabriel_couto.services.api.viaCepApi
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch
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

    private val _fotoFuncionario = MutableLiveData<Uri>()
    val fotoFuncionario: LiveData<Uri> = _fotoFuncionario

    private val _cep = MutableLiveData<Cep>()
    val cep: LiveData<Cep> = _cep

    init {
        fun buscaCep(cep: String) {
            viewModelScope.launch {
                try {
                    _cep.value = viaCepApi.getViaCepApiService().buscaEndereço(cep)
                } catch (e: Error) {
                    Log.e("updateCep", "${e.message}")
                }
            }
        }
    }

    fun setUpFotoFuncionario(email: String){
        try {
            val file = File.createTempFile("fotoPerfil", ".jpeg")
            val fileReference = firestorageService.downloadFotoFuncionario(email, file)
            fileReference
                .addOnSuccessListener {
                    _fotoFuncionario.value = file.toUri()
                }
                .addOnFailureListener {
                    Log.e("setUpFotoFuncionario", "${it.message}")
                }
        } catch (e: Error) {
            Log.e("setUpFotoFuncionario", "${e.message}")
        }
    }

    fun setFotoFuncionario(uri: Uri){
        try {
            _fotoFuncionario.value = uri
        } catch (e: Error) {
            Log.e("setFotoFuncionario", "${e.message}")
        }
    }

    fun buscaCep(cep: String) {
        viewModelScope.launch {
            try {
                _cep.value = viaCepApi.getViaCepApiService().buscaEndereço(cep)
            } catch (e: Error) {
                Log.e("updateCep", "${e.message}")
            }
        }
    }

}