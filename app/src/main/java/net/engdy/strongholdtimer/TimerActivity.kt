package net.engdy.strongholdtimer

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.util.SparseIntArray
import androidx.activity.ComponentActivity

open class TimerActivity : ComponentActivity() {
    var resFinishedSound = 0
    var resTickingSound = 0
    var resBackgroundSound = 0
    val resSoundAtTime = SparseIntArray()
    var playerForeground: MediaPlayer? = null
    var playerBackground: MediaPlayer? = null
    var isBackgroundSoundPlaying: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs: SharedPreferences = getPreferences(Context.MODE_PRIVATE)
        isBackgroundSoundPlaying = prefs.getBoolean(BACKGROUND_SOUND_PLAYING, false)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy()")
        playerForeground?.stop()
        playerForeground?.release()
        playerBackground?.stop()
        playerBackground?.release()
        super.onDestroy()
    }

    fun playBackgroundSound() {
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

    fun setSoundAtTime(res: Int, secs: Int) {
        resSoundAtTime.put(secs, res)
    }

    fun reset() {
        Log.d(TAG, "reset()")
    }

    companion object {
        val TAG = TimerActivity::class.simpleName
        const val BACKGROUND_SOUND_PLAYING = "neg.engdy.background_sound_playing"
    }
}