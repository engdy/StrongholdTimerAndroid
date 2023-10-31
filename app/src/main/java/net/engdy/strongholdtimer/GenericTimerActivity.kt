package net.engdy.strongholdtimer

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.engdy.strongholdtimer.ui.TimerViewModel
import net.engdy.strongholdtimer.ui.theme.Article27Theme

class GenericTimerActivity : TimerActivity(
    duration = FIVE_MINUTES_IN_MILLIS,
    finalTickingDuration = TEN_SECONDS_IN_MILLIS
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate()")
        super.onCreate(savedInstanceState)
        resFinishedSound = R.raw.buzzer
        resBackgroundSound = R.raw.generic_background
        resTickingSound = R.raw.ticking
        playBackgroundSound()
        setContent {
            Article27Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GenericTimer(
                        timerViewModel = timerViewModel
                    )
                }
            }
        }
    }

    companion object {
        val TAG = GenericTimerActivity::class.simpleName
        private const val FIVE_MINUTES_IN_MILLIS = 300_000L
        private const val TEN_SECONDS_IN_MILLIS = 10_000L
    }
}

@Composable
fun GenericTimer(
    modifier: Modifier = Modifier,
    timerViewModel: TimerViewModel = viewModel()
) {
    val timerUiState by timerViewModel.uiState.collectAsState()

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            Text(
                text = timerViewModel.secondsToString(timerUiState.secondsLeft),
                fontSize = 100.sp
            )
            Column {
                Button(
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        stringResource(R.string.set_time)
                    )
                }
                Button(
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = if (timerUiState.isRunning) {
                            stringResource(R.string.pause)
                        } else {
                            stringResource(R.string.start)
                        }
                    )
                }
                Button(
                    onClick = {
                        timerViewModel.resetTimer()
                    }
                ) {
                    Text(
                        stringResource(R.string.reset)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GenericTimerPreview() {
    Article27Theme {
        GenericTimer()
    }
}