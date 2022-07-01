package br.com.truckhospital.modules.util

object PairUtil {
    inline fun <reified F, reified S> pairOf(first: F, second: S) = Pair(first, second)
}