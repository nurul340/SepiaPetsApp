package com.sepiainnovations.petsapp.utils

sealed class DataResult<T>(
    val data: T? = null,
    val message: String? = null,
    val errorCode: Int? = null

) {
    class LOADING<T>: DataResult<T>()


    class SUCCESS<T>(
        data: T?
    ): DataResult<T>(
        data
    )


    class ERROR<T>(
        errorCode: Int, message: String?
    ): DataResult<T>(
        message= message,
        errorCode = errorCode
    )
}