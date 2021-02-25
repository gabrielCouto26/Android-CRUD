package br.edu.infnet.dr3_tp1_gabriel_couto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.navHostFragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.listaFuncionariosFragment, R.id.showFuncionarioFragment, R.id.formFuncionarioFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

    }
}