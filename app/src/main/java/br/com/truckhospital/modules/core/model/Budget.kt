package br.com.truckhospital.modules.core.model

import java.io.Serializable

data class Budget(
    val laborCost: Double,
    val partsCost: Double,
    val totalCost: Double
) : Serializable
