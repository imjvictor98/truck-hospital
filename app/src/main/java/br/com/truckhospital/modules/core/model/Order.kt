package br.com.truckhospital.modules.core.model

import java.io.Serializable

data class Order (
    val orderId: String,
    val description: String,
    val budget: Budget?,
    val client: Client?,
    val vehicle: Vehicle?,
    val complaint: Complaint?
) : Serializable