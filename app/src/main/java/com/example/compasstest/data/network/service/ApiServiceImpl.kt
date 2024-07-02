package com.example.compasstest.data.network.service

import com.example.compasstest.core.ConstantsUrls.URL_BASE_PATH
import com.example.compasstest.core.genericresults.CompassTestResult
import com.example.compasstest.core.modules.NetworkModule
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(
    private val api: NetworkModule
) : ApiService {
    override suspend fun buildBaseUrl(): String {
        return try {
            api.buildBaseUrl()
                .appendPath(URL_BASE_PATH)
                .build().toString()

        } catch (e: Exception) {
            e.message ?: "Couldn't build base url."
        }
    }

}