package com.example.compasstest.data.network.service

interface ApiService {
    suspend fun buildBaseUrl(): String
}