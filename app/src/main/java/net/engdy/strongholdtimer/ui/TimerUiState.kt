package net.engdy.strongholdtimer.ui

data class TimerUiState(
    val secondsLeft: Int = 300,
    val isRunning: Boolean = false,
    val isEnded: Boolean = false,
    val isFinalTicking: Boolean = false,
    val lastSecondPlayed: Int = -1
)
