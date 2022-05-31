package br.com.truckhospital.modules.core.model

import java.io.Serializable

data class Budget(
    val laborCost: Float,
    val partsCost: Float,
    val totalCost: Float
) : Serializable
