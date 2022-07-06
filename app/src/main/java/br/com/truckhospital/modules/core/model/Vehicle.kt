package br.com.truckhospital.modules.core.model

import java.io.Serializable

data class Vehicle(
    val plate: String = "",
    val brand: String = "",
    val model: String = ""
) : Serializable
