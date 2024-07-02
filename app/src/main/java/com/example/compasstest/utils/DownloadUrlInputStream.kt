package com.example.compasstest.utils

import com.example.compasstest.core.ConstantsResponses.RESPONSE_ERROR_NOT_FOUND
import com.example.compasstest.core.ConstantsResponses.RESPONSE_OK
import com.example.compasstest.core.genericresults.InputStreamXmlResult
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

class DownloadUrlInputStream @Inject constructor() {

    @Throws(IOException::class)
    fun downloadUrl(urlString: String): InputStreamXmlResult {
        val url = URL(urlString)
        var status: Int = -1
        val inputStream = (url.openConnection() as? HttpURLConnection)?.run {
            try {
                status = responseCode
                readTimeout = 10000
                connectTimeout = 15000
                requestMethod = "GET"
                connect()
                if (responseCode == RESPONSE_OK) {
                    inputStream
                } else null
            } catch (e: Exception) {
                status = RESPONSE_ERROR_NOT_FOUND
                null
            }
        }
        return InputStreamXmlResult(
            status = status,
            entity = inputStream
        )
    }
}