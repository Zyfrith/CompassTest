package com.example.compasstest.presentation.interfaces

interface HomeViewModelInterface {
    fun parseResponseWords(response: String): Map<String, Int>

    fun parseResponse10thCharacter(response: String): String
}