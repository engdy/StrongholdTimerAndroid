package net.engdy.strongholdtimer.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.engdy.strongholdtimer.SHGTimer

class Article27ViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(Article27UiState())
    val uiState: StateFlow<Article27UiState> = _uiState.asStateFlow()
    private var onTickCallback: ((Long) -> Unit)? = null
    private var onFinishCallback: (() -> Unit)? = null
    private val timer: SHGTimer = SHGTimer(
        duration = 300_000L,  // 5 minutes
        interval = 500L,
        tick = { millis ->
            onTick(millis)
        },
        finish = {
            onFinish()
        }
    )

    init {
        resetTimer()
    }

    fun resetTimer() {
        _uiState.value = Article27UiState()
        timer.reset()
    }

    fun startPauseTimer() {
        if (uiState.value.isRunning) {
            _uiState.update { currentState ->
                currentState.copy(
                    isRunning = false
                )
            }
            timer.cancel()
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    isRunning = true
                )
            }
            timer.start()
        }
    }

    fun setOnTickCallback(callback: (Long) -> Unit) {
        onTickCallback = callback
    }

    fun setFinishCallback(callback: () -> Unit) {
        onFinishCallback = callback
    }

    private fun onTick(millis: Long) {
        _uiState.update { currentState ->
            currentState.copy(
                secondsLeft = (millis / 1_000L).toInt()
            )
        }
        onTickCallback?.let { it(millis) }
    }

    private fun onFinish() {
        _uiState.update { currentState ->
            currentState.copy(
                isRunning = false,
                isEnded = true,
                secondsLeft = 0
            )
        }
        onFinishCallback?.let { it() }
    }
}