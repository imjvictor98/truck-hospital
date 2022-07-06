package br.com.truckhospital.modules.util.extension

fun <T>MutableList<*>.toListOf(): List<T> {
    return this as List<T>
}