package com.example.compasstest.core.genericresults

data class CompassTestResult<Result>(
    var entity: Result? = null,
    var exception: Throwable? = null
)