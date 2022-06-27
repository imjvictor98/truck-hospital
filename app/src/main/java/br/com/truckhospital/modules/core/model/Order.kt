package br.com.truckhospital.modules.core.model

import java.io.Serializable

data class Order (
    val orderId: String? = null,
    val budget: Budget? = null,
    val client: Client? = null,
    val vehicle: Vehicle? = null,
    val service: Complaint? = null,
    val complaint: Complaint? = null
) : Serializable