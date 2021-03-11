package br.edu.infnet.dr3_tp1_gabriel_couto.services.api

import retrofit2.http.*

interface ViaCepApiService{

    @GET("{cep}/json/unicode/")
    suspend fun buscaEndereço(@Path("cep") cep: String)
    // 01001000

}