package net.engdy.strongholdtimer

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import net.engdy.strongholdtimer.ui.theme.StrongholdTimerTheme

class GameSelectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate()")
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        setContent {
            StrongholdTimerTheme {
                GameSelect()
            }
        }
    }
    companion object {
        val TAG = GameSelectActivity::class.simpleName
    }
}

@Composable
fun GameSelectHeader(
    modifier: Modifier = Modifier
) {
    Text(text = "Header")
}

@Composable
fun GameSelectList(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    LazyRow(
        modifier = modifier.fillMaxHeight(.8f),
        contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.padding_medium)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item {
            Image(
                painter = painterResource(R.drawable.article27),
                contentDescription = stringResource(R.string.article_27_box_top),
                modifier = Modifier.clickable {
                    Log.d(GameSelectActivity.TAG, "Clicked Article 27")
                    context.startActivity(Intent(context, Article27Activity::class.java))
                }
            )
        }
        item {
            Image(
                painter = painterResource(R.drawable.spacecadets),
                contentDescription = stringResource(R.string.spacecadets_box_top),
                modifier = Modifier.clickable {
                    Log.d(GameSelectActivity.TAG, "Clicked Space Cadets")
                    context.startActivity(Intent(context, SpaceCadetsActivity::class.java))
                }
            )
        }
        item {
            Image(
                painter = painterResource(R.drawable.timenspace),
                contentDescription = stringResource(R.string.timenspace_box_top),
                modifier = Modifier.clickable {
                    Log.d(GameSelectActivity.TAG, "Clicked Time 'n' Space")
                    context.startActivity(Intent(context, TimeNSpaceActivity::class.java))
                }
            )
        }
        item {
            Image(
                painter = painterResource(R.drawable.ggg),
                contentDescription = stringResource(R.string.goingoinggone_box_top),
                modifier = Modifier.clickable {
                    Log.d(GameSelectActivity.TAG, "Clicked Going Going Gone")
                    context.startActivity(Intent(context, GoingGoingGoneActivity::class.java))
                }
            )
        }
        item {
            Image(
                painter = painterResource(R.drawable.spacesheep),
                contentDescription = stringResource(R.string.spacesheep_box_top),
                modifier = Modifier.clickable {
                    Log.d(GameSelectActivity.TAG, "Clicked Space Sheep")
                    context.startActivity(Intent(context, SpaceSheepActivity::class.java))
                }
            )
        }
        item {
            Image(
                painter = painterResource(R.drawable.generic),
                contentDescription = stringResource(R.string.generic_box_top),
                modifier = Modifier.clickable {
                    Log.d(GameSelectActivity.TAG, "Clicked Generic")
                    context.startActivity(Intent(context, GenericTimerActivity::class.java))
                }
            )
        }
    }
}

@Composable
fun GameSelectFooter(
    modifier: Modifier = Modifier
) {
    Text("Footer")
}

@Composable
fun GameSelect(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF333333),
                        Color(0xFF555555),
                    )
                )
            )
        ) {
        Column {
            GameSelectHeader(
                modifier = Modifier.fillMaxHeight(.1f)
            )
            GameSelectList(
                modifier = Modifier.fillMaxHeight(.8f)
            )
            GameSelectFooter(
                modifier = Modifier.fillMaxHeight(.1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameSelectPreview() {
    StrongholdTimerTheme {
        GameSelect()
    }
}