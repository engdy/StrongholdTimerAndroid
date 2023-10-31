package net.engdy.strongholdtimer

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import net.engdy.strongholdtimer.ui.TimerViewModel
import net.engdy.strongholdtimer.ui.theme.Article27Theme

class Article27Activity : TimerActivity(
    duration = FIVE_MINUTES_IN_MILLIS,
    finalTickingDuration = TEN_SECONDS_IN_MILLIS
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate()")
        super.onCreate(savedInstanceState)
        resFinishedSound = R.raw.buzzer
        resBackgroundSound = R.raw.article27_background
        resTickingSound = R.raw.ticking
        playBackgroundSound()
        setSoundAtTime(R.raw.gavel5, 299)
        setSoundAtTime(R.raw.gavel4, 240)
        setSoundAtTime(R.raw.gavel3, 180)
        setSoundAtTime(R.raw.gavel2, 120)
        setSoundAtTime(R.raw.gavel1, 60)
        setSoundAtTime(R.raw.parliament, 10)
        setSoundAtTime(R.raw.gavel_final, 3)
        setContent {
            Article27Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Article27Timer(
                        timerViewModel = timerViewModel
                    )
                }
            }
        }
        reset()
    }

    companion object {
        val TAG = Article27Activity::class.simpleName
        private const val TEN_SECONDS_IN_MILLIS = 10_000L
        private const val FIVE_MINUTES_IN_MILLIS = 300_000L
    }
}

@Composable
fun Article27Timer(
    modifier: Modifier = Modifier,
    timerViewModel: TimerViewModel = viewModel()
) {
    val image = painterResource(R.drawable.article27)
    val timerUiState by timerViewModel.uiState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painterResource(R.drawable.darkwood),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Image(
                painter = image,
                contentDescription = stringResource(R.string.article_27_box_top),
                modifier
                    .padding(dimensionResource(R.dimen.padding_medium))
                    .fillMaxHeight(0.75f)
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = modifier.fillMaxHeight(0.7f),
                    text = timerViewModel.secondsToString(timerUiState.secondsLeft),
                    fontSize = 50.em
                )
                Row() {
                    Button(
                        onClick = {
                            timerViewModel.startPauseTimer()
                        }
                    ) {
                        Text(
                            text = if (timerUiState.isRunning) {
                                stringResource(R.string.pause)
                            } else {
                                stringResource(R.string.start)
                            }
                        )
                    }
                    Spacer(
                        modifier = Modifier.width(dimensionResource(R.dimen.padding_medium))
                    )
                    Button(
                        onClick = {
                            timerViewModel.resetTimer()
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.reset)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Article27Preview() {
    Article27Theme {
        Article27Timer()
    }
}