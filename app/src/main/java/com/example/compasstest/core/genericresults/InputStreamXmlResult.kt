package com.example.compasstest.core.genericresults

import java.io.InputStream

data class InputStreamXmlResult (
    val status: Int,
    val entity: InputStream?
)