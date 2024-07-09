package com.live.tasknew.network

enum class ErrorType {
    NETWORK, // IO
    TIMEOUT, // Socket
    UNKNOWN, //Anything else
    UNAUTHORIZED
}
