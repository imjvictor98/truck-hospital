package br.com.truckhospital.modules.core.model

import java.io.Serializable

data class Budget(
    val laborCost: Double = 0.0,
    val partsCost: Double = 0.0,
    val totalCost: Double = 0.0
) : Serializable
