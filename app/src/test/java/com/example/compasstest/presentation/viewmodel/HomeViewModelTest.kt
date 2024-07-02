package com.example.compasstest.presentation.viewmodel

import com.example.compasstest.presentation.processors.HomeProcessor
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private lateinit var mockProcessor: HomeProcessor
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        mockProcessor = mockk()
        viewModel = HomeViewModel(mockProcessor)
    }

    @Test
    fun parseResponseWords_emptyResponse_returnsEmptyMap() {
        val result = viewModel.parseResponseWords("")
        assertEquals(emptyMap<String, Int>(), result)
    }

    @Test
    fun parseResponseWords_singleWordResponse_returnsMapWithSingleEntry() {
        val result = viewModel.parseResponseWords("hello")
        assertEquals(mapOf("hello" to 1), result)
    }

    @Test
    fun parseResponseWords_multipleWordsResponse_returnsMapWithCorrectCounts() {
        val result = viewModel.parseResponseWords("hello world hello kotlin")
        assertEquals(mapOf("hello" to 2, "world" to 1, "kotlin" to 1), result)
    }

    @Test
    fun parseResponse10thCharacter_emptyResponse_returnsEmptyString() {
        val result = viewModel.parseResponse10thCharacter("")
        assertEquals("", result)
    }

    @Test
    fun parseResponse10thCharacter_shortResponse_returnsEmptyString() {
        val result = viewModel.parseResponse10thCharacter("abcdefg")
        assertEquals("", result)
    }

    @Test
    fun parseResponse10thCharacter_longResponse_returnsCorrectCharacters() {
        val result = viewModel.parseResponse10thCharacter("abcdefghijklmnopqrstuvwxyz0123456789")
        assertEquals("jt3", result)
    }

    @Test
    fun parseResponse10thCharacter_symbolsResponse_returnsCorrectCharacters() {
        val result = viewModel.parseResponse10thCharacter("sddafdsa;[435][sdf0-9/dsf.,23 ")
        assertEquals("[- ", result)
    }
}