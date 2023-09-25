package net.engdy.strongholdtimer

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import net.engdy.strongholdtimer.ui.theme.StrongholdTimerTheme

class GameSelectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate()")
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        requestWindowFeature(Window.FEATURE_NO_TITLE)
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
fun GameSelect(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyRow {
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
}

@Preview(showBackground = true)
@Composable
fun GameSelectPreview() {
    StrongholdTimerTheme {
        GameSelect()
    }
}