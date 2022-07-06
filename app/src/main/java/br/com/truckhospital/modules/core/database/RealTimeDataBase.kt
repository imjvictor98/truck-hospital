package br.com.truckhospital.modules.core.database

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object RealTimeDataBase {
    val dataBase get() = Firebase.database
}