package io.bsamartins.spqr.federation.annotation

@Target(AnnotationTarget.FUNCTION)
annotation class DataFetcher(
    val type: String
)
