package br.com.truckhospital.modules.util.extension

inline fun <reified T>Collection<T>.toListOf(): List<T> {
    return this.toList()
}