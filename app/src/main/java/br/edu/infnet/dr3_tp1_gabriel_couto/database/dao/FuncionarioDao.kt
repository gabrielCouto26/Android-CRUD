package br.edu.infnet.dr3_tp1_gabriel_couto.database.dao

import br.edu.infnet.dr3_tp1_gabriel_couto.models.Funcionario
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import java.io.File

interface FuncionarioDao {

    fun findAll(): CollectionReference
    fun findById(email: String): Task<DocumentSnapshot>
    fun findBy(filtro: String): Task<QuerySnapshot>
    fun insert(funcionario: Funcionario): Task<Void>
    //fun update(funcionario: Funcionario)
    fun delete(email: String): Task<Void>
}
