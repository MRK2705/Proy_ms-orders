package com.example.msorders.dto

data class ResponseDto<T> (
    val data: T,
    val message: String,
    val success: Boolean
)