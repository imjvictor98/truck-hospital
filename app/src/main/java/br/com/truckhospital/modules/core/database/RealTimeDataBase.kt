package br.com.truckhospital.modules.core.database

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object RealTimeDataBase {
    val dataBase get() = Firebase.database

    fun getChildReferenceAt(vararg nodes: String): DatabaseReference {
        val referenceAt = Firebase.database.reference
        for (node in nodes) {
            referenceAt.child(node)
        }
        return referenceAt
    }
}