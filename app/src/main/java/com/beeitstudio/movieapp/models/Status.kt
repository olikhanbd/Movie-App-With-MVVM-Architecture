package com.beeitstudio.movieapp.models

enum class Status {
    SUCCESS,
    LOADING,
    ERROR;

    fun isLoading() = this == LOADING
}