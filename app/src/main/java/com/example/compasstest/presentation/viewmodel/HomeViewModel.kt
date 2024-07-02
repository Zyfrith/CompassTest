package com.example.compasstest.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compasstest.presentation.actions.HomeAction
import com.example.compasstest.presentation.intents.HomeUserIntent
import com.example.compasstest.presentation.processors.HomeProcessor
import com.example.compasstest.presentation.results.HomeResult
import com.example.compasstest.presentation.states.HomeUiState
import com.example.compasstest.mvi.MviPresentation
import com.example.compasstest.presentation.interfaces.HomeViewModelInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeProcessor: HomeProcessor
) : ViewModel(), MviPresentation<HomeUserIntent, HomeUiState>, HomeViewModelInterface {

    override fun processUserIntent(userIntent: HomeUserIntent) {
        when (userIntent) {
            is HomeUserIntent.NullUserIntent -> {}
            is HomeUserIntent.InitialUserIntent -> processUserIntent(HomeUserIntent.GetDataIntent)
            is HomeUserIntent.GetDataIntent -> getData()
        }
    }

    /**
     * This method could launch a single result and then parse the response into the Char list
     * and then the words list, but since the requirement was to make two asynchronous calls
     * with the same API, this process was simply made twice.
     **/
    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = homeProcessor.dispatchAction(HomeAction.GetDataHomeAction)) {
                is HomeResult.GetData.Success -> {
                    updateComponent(tenthsList = parseResponse10thCharacter(result.response))
                }

                is HomeResult.GetData.Error -> {
                    Log.d("GetData Error", "Error retrieving the data")
                }

                else -> {}
            }
            when (val result = homeProcessor.dispatchAction(HomeAction.GetDataHomeAction)) {
                is HomeResult.GetData.Success -> {
                    updateComponent(wordsList = parseResponseWords(result.response))
                }

                is HomeResult.GetData.Error -> {
                    Log.d("GetData Error", "Error retrieving the data")
                }

                else -> {}
            }
        }
    }

    override fun parseResponseWords(response: String): Map<String, Int> {
            return response.split(" ")
                .groupingBy { it }.eachCount().filter { it.value > 0 && it.key.isNotEmpty() }
    }

    override fun parseResponse10thCharacter(response: String): String {
        return response.filterIndexed { index, _ -> (index + 1) % 10 == 0 }
    }

    override val uiIntents = Channel<HomeUserIntent>()
    override val uiStates = MutableStateFlow<HomeUiState>(HomeUiState.NullUiState)

    override fun setUiState(state: HomeUiState) {
        viewModelScope.launch {
            uiStates.emit(state)
        }
    }

    override fun uiStates(): StateFlow<HomeUiState> {
        return uiStates
    }

    private val _uiComponentState = MutableStateFlow(HomeUiComponentState())
    val uiComponentState: StateFlow<HomeUiComponentState> = _uiComponentState.asStateFlow()

    fun updateComponent(
        wordsList: Map<String, Int>? = null,
        tenthsList: String? = null,
        isTenthsDropdownVisible: Boolean? = null,
        isWordsDropdownVisible: Boolean? = null
    ) {
        _uiComponentState.update { currentState ->
            currentState.copy(
                wordsList = wordsList ?: currentState.wordsList,
                tenthsList = tenthsList ?: currentState.tenthsList,
                isWordsDropdownVisible = isWordsDropdownVisible
                    ?: currentState.isWordsDropdownVisible,
                isTenthsDropdownVisible = isTenthsDropdownVisible
                    ?: currentState.isTenthsDropdownVisible
            )
        }
    }

}

data class HomeUiComponentState(
    val wordsList: Map<String, Int>? = null,
    val tenthsList: String? = null,
    val isTenthsDropdownVisible: Boolean = false,
    val isWordsDropdownVisible: Boolean = false
)