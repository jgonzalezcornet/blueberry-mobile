package com.example.blueberry.data

class DataSourceException(
    var code: Int,
    message: String,
) : Exception(message)