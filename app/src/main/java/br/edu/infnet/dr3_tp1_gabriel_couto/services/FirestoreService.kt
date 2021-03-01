package br.edu.infnet.dr3_tp1_gabriel_couto.services

import br.edu.infnet.dr3_tp1_gabriel_couto.models.Funcionario
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class FirestoreService() {
    private val collection = FirebaseFirestore.getInstance().collection("funcionarios")

    fun findAll(): CollectionReference {
        return collection
    }

    fun findById(email: String): Task<DocumentSnapshot> {
        return collection.document(email).get()
    }

    fun findBy(filtro: String): Task<QuerySnapshot> {
        return collection
                .whereEqualTo(filtro, filtro)
                .get()
    }

    fun insert(id: String, funcionario: Funcionario): Task<Void> {
        return collection.document(id).set(funcionario)
    }

    /*override fun update(funcionario: Funcionario) {
        insert(funcionario)
    }*/

    fun delete(emailFuncionario: String): Task<Void> {
        return collection.document(emailFuncionario).delete()
    }
}