package br.com.truckhospital.modules.core.model

import java.io.Serializable

data class Client(
    val cnpj: String? = null,
    val name: String? = null,
    val phone: String?= null,
    val cep: String? = null
) : Serializable
