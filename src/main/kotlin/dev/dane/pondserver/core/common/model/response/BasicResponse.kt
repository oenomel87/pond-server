package dev.dane.pondserver.core.common.model.response

data class BasicResponse<T>(
    val code : String,
    val message : String,
    val data : T?
)