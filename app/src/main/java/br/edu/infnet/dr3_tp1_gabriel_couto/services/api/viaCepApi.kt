package br.edu.infnet.dr3_tp1_gabriel_couto.services.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object viaCepApi {
    private val url = "https://viacep.com.br/ws/"

    private var instance: Retrofit? = null
    private fun getInstance(): Retrofit {
        if (instance == null){
            instance = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance as Retrofit
    }

    fun getViaCepApiService(): ViaCepApiService = getInstance().create(ViaCepApiService::class.java)
}