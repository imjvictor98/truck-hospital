package br.com.truckhospital.modules.core.model

import java.io.Serializable

data class Client(
    val cnpj: String,
    val name: String,
    val phone: String,
    val cep: String
) : Serializable
