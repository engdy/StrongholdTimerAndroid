package net.engdy.strongholdtimer

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import net.engdy.strongholdtimer.ui.theme.StrongholdTimerTheme

class SplooshActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate()")
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContent {
            StrongholdTimerTheme {
                Splash(version = "1.0.0")
            }
        }
        Handler(Looper.getMainLooper()).postDelayed({
//            val intent = Intent(this, GameSelectActivity::class.java)
            val intent = Intent(this, Article27Activity::class.java)
            Log.d(TAG, "finish()")
            startActivity(intent)
            finish()
        }, 3000)
    }
    companion object {
        val TAG = SplooshActivity::class.simpleName
    }
}

@Composable
fun Credits(version: String) {
    Column {
        Text(text = stringResource(R.string.app_name))
        Text(text = stringResource(R.string.app_version, version))
        Text(text = stringResource(R.string.developer))
    }
}

@Composable
fun Splash(version: String) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Row() {
            Image(
                painter = painterResource(id = R.drawable.ic_stronghold),
                contentDescription = stringResource(
                    id = R.string.splash_content_description
                )
            )
            Credits(version)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    StrongholdTimerTheme() {
        Splash("0.9.9")
    }
}
