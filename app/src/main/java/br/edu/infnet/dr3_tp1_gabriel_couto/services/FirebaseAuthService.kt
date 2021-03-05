package br.edu.infnet.dr3_tp1_gabriel_couto.services

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthService {
    val firebaseAuth = FirebaseAuth.getInstance()
    fun signIn(email: String, senha: String): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(email, senha)
    }

    fun createUserWithEmailAndPassword(email: String, senha: String): Task<AuthResult> {
        return firebaseAuth.createUserWithEmailAndPassword(email, senha)
    }

    fun isLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    fun logout(){
        firebaseAuth.signOut()
    }
}