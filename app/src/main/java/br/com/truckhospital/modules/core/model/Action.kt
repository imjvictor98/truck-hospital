package br.com.truckhospital.modules.core.model

import androidx.annotation.DrawableRes
import br.com.truckhospital.R
import java.io.Serializable

enum class ActionType(val text: String): Serializable {
    WHATSAPP("Enviar WhatsApp"),
    DOWNLOAD_PDF("Baixar PDF")
}

data class ActionTypeIconDTO(
    @DrawableRes val icon: Int
) : Serializable

fun ActionType.toIconDTO() : ActionTypeIconDTO {
    return ActionTypeIconDTO(
        when (this) {
            ActionType.WHATSAPP -> R.drawable.ic_whatsapp
            else -> R.drawable.ic_details
        }
    )
}

interface ActionListener {
    fun onCreatePDFCLick()
    fun onCreateAndSendWhatsApp()
}