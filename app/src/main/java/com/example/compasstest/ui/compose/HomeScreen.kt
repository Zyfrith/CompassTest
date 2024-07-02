package com.example.compasstest.ui.compose

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compasstest.R
import com.example.compasstest.presentation.intents.HomeUserIntent
import com.example.compasstest.presentation.states.HomeUiState
import com.example.compasstest.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val state: HomeUiState by viewModel.uiStates.collectAsStateWithLifecycle(initialValue = HomeUiState.InitialUiState)
    val componentState by viewModel.uiComponentState.collectAsStateWithLifecycle()

    when (state) {
        is HomeUiState.NullUiState -> {}
        is HomeUiState.InitialUiState -> viewModel.processUserIntent(HomeUserIntent.InitialUserIntent)
    }

    HomeScreenView(
        context = context,
        wordsList = componentState.wordsList ?: emptyMap(),
        tenthsList = componentState.tenthsList,
        isTenthsDropdownVisible = componentState.isTenthsDropdownVisible,
        setTenthsDropdownVisible = { viewModel.updateComponent(isTenthsDropdownVisible = it) },
        isWordsDropdownVisible = componentState.isWordsDropdownVisible,
        setWordsDropdownVisible = { viewModel.updateComponent(isWordsDropdownVisible = it) }
    )

}

@Composable
fun HomeScreenView(
    context: Context,
    tenthsList: String?,
    wordsList: Map<String, Int>,
    isTenthsDropdownVisible: Boolean,
    setTenthsDropdownVisible: (Boolean) -> Unit,
    isWordsDropdownVisible: Boolean,
    setWordsDropdownVisible: (Boolean) -> Unit
) {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (wordsContainer, tenthsContainer) = createRefs()

        Column(
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .constrainAs(wordsContainer) {
                    top.linkTo(parent.top)
                },
        ) {
            Text(
                modifier = Modifier
                    .clickable { setTenthsDropdownVisible(!isTenthsDropdownVisible) },
                text = context.getString(R.string.tenths_title)
            )
            CommonDropdown(
                isDropdownVisible = isTenthsDropdownVisible,
                setDropdownVisibility = setTenthsDropdownVisible,
                dropdownContent = {
                    Text(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .background(color = MaterialTheme.colorScheme.background),
                        text = tenthsList ?: ""
                    )
                }
            )
        }

        Column(
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(tenthsContainer) {
                    top.linkTo(wordsContainer.bottom)
                },
        ) {
            Text(
                modifier = Modifier
                    .clickable { setWordsDropdownVisible(!isWordsDropdownVisible) },
                text = context.getString(R.string.words_title)
            )
            CommonDropdown(
                isDropdownVisible = isWordsDropdownVisible,
                setDropdownVisibility = setWordsDropdownVisible,
                dropdownContent = {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .background(color = MaterialTheme.colorScheme.background)
                    ) {
                        for (word in wordsList) {
                            Text(text = context.getString(R.string.words_text, word.key, word.value))
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun CommonDropdown(
    modifier: Modifier = Modifier,
    isDropdownVisible: Boolean,
    setDropdownVisibility: (Boolean) -> Unit,
    dropdownContent: @Composable (AnimatedVisibilityScope.() -> Unit)
) {
    AnimatedVisibility(
        modifier = modifier.clickable { setDropdownVisibility(!isDropdownVisible) },
        visible = isDropdownVisible,
        enter = slideInHorizontally(initialOffsetX = { it }),
        exit = slideOutHorizontally(targetOffsetX = { it }),
        content = dropdownContent
    )
}