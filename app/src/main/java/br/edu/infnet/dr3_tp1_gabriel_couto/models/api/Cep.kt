package br.edu.infnet.dr3_tp1_gabriel_couto.models.api

class Cep(
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val ibge: String,
    val gia: String,
    val ddd: String,
    val siafi: String,
) {
    override fun toString(): String {
        return "$bairro, $localidade - $uf"
    }
}