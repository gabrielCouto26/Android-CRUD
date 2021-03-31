package br.edu.infnet.dr3_tp1_gabriel_couto.database.dao

import br.edu.infnet.dr3_tp1_gabriel_couto.models.Funcionario
import br.edu.infnet.dr3_tp1_gabriel_couto.services.FirestoreService
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

class FuncionarioDaoImpl(private val firestoreService: FirestoreService) : FuncionarioDao {

    override fun findAll(): CollectionReference {
        return firestoreService.findAll()
    }

    override fun findById(email: String): Task<DocumentSnapshot> {
        return firestoreService.findById(email)
    }

    override fun findBy(filtro: String): Task<QuerySnapshot> {
        return firestoreService.findBy(filtro)
    }

    override fun insertOrUpdate(funcionario: Funcionario): Task<Void> {
        val id = funcionario.email.toString()
        return firestoreService.insert(id, funcionario)
    }

    /*override fun update(funcionario: Funcionario) {
        insert(funcionario)
    }*/

    override fun delete(emailFuncionario: String): Task<Void> {
        return firestoreService.delete(emailFuncionario)
    }

}