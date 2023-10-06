package net.engdy.strongholdtimer.ui

import android.util.Log
import android.util.SparseIntArray
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.engdy.strongholdtimer.SHGTimer

open class TimerViewModel(
    duration: Long,
    val soundsAtTime: SparseIntArray = SparseIntArray(),
    val finalTickingDuration: Long = TEN_SECONDS_IN_MILLIS
) : ViewModel() {
    private val originalDuration = duration
    private val _uiState = MutableStateFlow(TimerUiState(secondsLeft = (duration / 1_000L).toInt()))
    val uiState: StateFlow<TimerUiState> = _uiState.asStateFlow()
    private val timer: SHGTimer = SHGTimer(
        duration = duration,
        interval = 500L,
        tick = { millis ->
            onTick(millis)
        },
        finish = {
            onFinish()
        }
    )
    private var onTickCallback: ((Long) -> Unit)? = null
    private var onFinishCallback: (() -> Unit)? = null
    private var onPlayFinalTicking: (() -> Unit)? = null
    private var onPlaySound:((Int) -> Unit)? = null

    protected open fun onTick(millis: Long) {
        Log.d(TAG, "onTick($millis)")
        val secondsLeft = (millis / 1_000L).toInt()
        _uiState.update { currentState ->
            currentState.copy(
                secondsLeft = secondsLeft
            )
        }
        if (_uiState.value.lastSecondPlayed != secondsLeft && soundsAtTime[secondsLeft] > 0) {
            _uiState.update { currentState ->
                currentState.copy(
                    lastSecondPlayed = secondsLeft
                )
            }
            onPlaySound?.let { it(soundsAtTime[secondsLeft]) }
        }
        if (!uiState.value.isFinalTicking && millis < finalTickingDuration) {
            _uiState.update { currentState ->
                currentState.copy(
                    isFinalTicking = true
                )
            }
            onPlayFinalTicking?.let { it() }
        }
        onTickCallback?.let { it(millis) }
    }

    protected open fun onFinish() {
        Log.d(TAG, "onFinish()")
        _uiState.update { currentState ->
            currentState.copy(
                isRunning = false,
                isEnded = true,
                secondsLeft = 0
            )
        }
        onFinishCallback?.let { it() }
    }

    fun setOnTickCallback(callback: (Long) -> Unit) {
        onTickCallback = callback
    }

    fun setFinishCallback(callback: () -> Unit) {
        onFinishCallback = callback
    }

    fun setPlayFinalTickingCallback(callback: () -> Unit) {
        onPlayFinalTicking = callback
    }

    fun setPlaySoundCallback(callback: (Int) -> Unit) {
        onPlaySound = callback
    }

    fun resetTimer() {
        Log.d(TAG, "resetTimer()")
        timer.reset()
        _uiState.value = TimerUiState(secondsLeft = (originalDuration / 1_000L).toInt())
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

    companion object {
        val TAG = TimerViewModel::class.simpleName
        const val TEN_SECONDS_IN_MILLIS = 10_000L
    }
}