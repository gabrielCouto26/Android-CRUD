package br.edu.infnet.dr3_tp1_gabriel_couto.ui.funcionario.form

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.*
import br.edu.infnet.dr3_tp1_gabriel_couto.database.dao.FuncionarioDao
import br.edu.infnet.dr3_tp1_gabriel_couto.models.Funcionario
import br.edu.infnet.dr3_tp1_gabriel_couto.services.FirestorageService
import java.io.File

class FormFuncionarioViewModel(
        private val funcionarioDao: FuncionarioDao,
        application: Application,
        private val firestorageService: FirestorageService
) : AndroidViewModel(application) {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    private val _fotoFuncionario = MutableLiveData<Uri>()
    val fotoFuncionario: LiveData<Uri> = _fotoFuncionario

    init {
        _status.value = false
        _msg.value = ""
    }

    fun store(nome: String, funcao: String, empresa: String, email: String){
        _status.value = false
        val funcionario = Funcionario(nome, funcao, empresa, email)
        val realizouUpload: Boolean = uploadFotoFuncionario(email)

        if(realizouUpload){
            funcionarioDao.insertOrUpdate(funcionario)
                    .addOnSuccessListener {
                        _status.value = true
                        _msg.value = "Persistência realizada com sucesso."
                    }
                    .addOnFailureListener{
                        _msg.value = "Problema ao persistir os dados."
                    }
        } else {
            _msg.value = "Erro ao cadastrar. Selecione uma foto."
        }

    }

    fun deleteFuncionario(emailFuncionario: String){
        funcionarioDao.delete(emailFuncionario)
        firestorageService.deleteFotoFuncionario(emailFuncionario)
    }

    fun downloadFotoFuncionario(emailFuncionario: String){
        val file = File.createTempFile("funcionario", ".png")
        val task = firestorageService.downloadFotoFuncionario(emailFuncionario, file)
        task
            .addOnSuccessListener {
                _fotoFuncionario.value = file.toUri()
            }
            .addOnFailureListener{
                _msg.value = "Falhou: ${it.message}"
            }
    }

    fun setFotoFuncionario(uri: Uri){
        _fotoFuncionario.value = uri
    }

    private fun uploadFotoFuncionario(emailFuncionario: String): Boolean{
        var realizouUpload = false
        val fotoFuncionario: Boolean = _fotoFuncionario.value != null

        if(fotoFuncionario){
            val fotoFuncionarioUri = _fotoFuncionario.value
            _msg.value = firestorageService.uploadFotoFuncionario(emailFuncionario, fotoFuncionarioUri!!)
            realizouUpload = true
        } else {
            _msg.value = "Erro: Selecione uma foto."
        }

        return realizouUpload
    }

}