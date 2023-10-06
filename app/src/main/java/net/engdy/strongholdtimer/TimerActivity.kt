package net.engdy.strongholdtimer

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.util.SparseIntArray
import android.view.Window
import androidx.activity.ComponentActivity
import net.engdy.strongholdtimer.ui.TimerViewModel

open class TimerActivity(
    protected val duration: Long,
    private val finalTickingDuration: Long = TEN_SECONDS_IN_MILLIS
) : ComponentActivity() {
    var resTickingSound = 0
    var resFinishedSound = 0
    var resBackgroundSound = 0
    private val resSoundAtTime = SparseIntArray()
    private var playerForeground: MediaPlayer? = null
    private var playerTicking: MediaPlayer? = null
    private var playerBackground: MediaPlayer? = null
    private var isBackgroundSoundPlaying: Boolean = false
    protected lateinit var timerViewModel: TimerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val prefs: SharedPreferences = getPreferences(Context.MODE_PRIVATE)
        isBackgroundSoundPlaying = prefs.getBoolean(PREF_BACKGROUND_SOUND_PLAYING, true)
        timerViewModel = TimerViewModel(
            duration = duration,
            soundsAtTime = resSoundAtTime,
            finalTickingDuration = finalTickingDuration
        )
        timerViewModel.setPlaySoundCallback { resId ->
            if (resId > 0) {
                playSound(resId)
            }
        }
        timerViewModel.setPlayFinalTickingCallback {
            if (resTickingSound > 0) {
                playTickingSound(resTickingSound)
            }
        }
        timerViewModel.setFinishCallback {
            reset()
        }
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy()")
        playerForeground?.stop()
        playerForeground?.release()
        playerBackground?.stop()
        playerBackground?.release()
        super.onDestroy()
    }

    protected fun playBackgroundSound() {
        Log.d(TAG, "playBackgroundSound()")
        if (isBackgroundSoundPlaying && resBackgroundSound > 0) {
            Log.d(TAG, "Playing background sound $resBackgroundSound")
            playerBackground = MediaPlayer.create(this, resBackgroundSound)
            playerBackground?.isLooping = true
            playerBackground?.start()
        } else {
            Log.d(TAG, "Can't play background sound: isBackgroundSoundPlaying " +
                    "= $isBackgroundSoundPlaying, resBackgroundSound = $resBackgroundSound")
        }
    }

    private fun playSound(resId: Int) {
        Log.d(TAG, "playSound($resId)")
        if (playerForeground?.isPlaying == true) {
            playerForeground?.stop()
            playerForeground?.release()
        }
        playerForeground = MediaPlayer.create(this, resId)
        playerForeground?.start()
    }

    private fun playTickingSound(resId: Int) {
        Log.d(TAG, "playLoopingSound($resId)")
        if (playerTicking?.isPlaying == true) {
            playerTicking?.stop()
            playerTicking?.release()
        }
        playerTicking = MediaPlayer.create(this, resId)
        playerTicking?.isLooping = true
        playerTicking?.start()
    }

    protected fun setSoundAtTime(res: Int, secs: Int) {
        resSoundAtTime.put(secs, res)
    }

    fun reset() {
        Log.d(TAG, "reset()")
        playerForeground?.stop()
        playerForeground?.release()
        playerForeground = null
        playerTicking?.stop()
        playerTicking?.release()
        playerTicking = null
    }

    companion object {
        val TAG = TimerActivity::class.simpleName
        const val TEN_SECONDS_IN_MILLIS = 10_000L
        const val PREF_BACKGROUND_SOUND_PLAYING = "neg.engdy.background_sound_playing"
    }
}