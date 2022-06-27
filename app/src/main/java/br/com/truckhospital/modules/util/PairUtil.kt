package br.com.truckhospital.modules.util

inline fun <reified F, reified S> pairOf(first: F, second: S) = Pair(first, second)