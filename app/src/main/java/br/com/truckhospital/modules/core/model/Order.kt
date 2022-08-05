package br.com.truckhospital.modules.core.model

import br.com.truckhospital.modules.core.model.Budget
import br.com.truckhospital.modules.core.model.Client
import br.com.truckhospital.modules.core.model.Complaint
import br.com.truckhospital.modules.core.model.Vehicle
import java.io.Serializable

data class Order (
    val orderId: String? = null,
    val budget: Budget? = null,
    val client: Client? = null,
    val vehicle: Vehicle? = null,
    val service: Complaint? = null,
    val complaint: Complaint? = null
) : Serializable