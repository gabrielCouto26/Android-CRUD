package br.edu.infnet.dr3_tp1_gabriel_couto.services

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class FirestorageService {

    fun downloadFotoFuncionario(emailFuncionario: String, file: File): FileDownloadTask {
        val fileReference = getFileReference(emailFuncionario)
        return fileReference.getFile(file)
    }

    fun uploadFotoFuncionario(emailFuncionario: String, fotoFuncionarioUri: Uri): String{
        var msg = ""
        val fileReference = getFileReference(emailFuncionario)
        val task = fileReference.putFile(fotoFuncionarioUri)
        task
            .addOnSuccessListener {
                msg = "Imagem carregada com sucesso"
            }
            .addOnFailureListener {
                msg = "Falhou: ${it.message}"
            }

        return msg
    }

    fun deleteFotoFuncionario(emailFuncionario: String){
        val fileReference = getFileReference(emailFuncionario)
        fileReference.delete()
    }

    private fun getFileReference(emailFuncionario: String): StorageReference {
        val storageReference = FirebaseStorage.getInstance().reference
        val fileReference = storageReference.child("imagens/$emailFuncionario.png")
        return fileReference
    }

}