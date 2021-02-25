package br.edu.infnet.dr3_tp1_gabriel_couto.models

import com.google.firebase.firestore.DocumentId


class Funcionario(
    var nome: String? = null,
    var funcao: String? = null,
    var empresa: String? = null,
    var email: String? = null,
    @DocumentId
    var id: String? = null
) {
}