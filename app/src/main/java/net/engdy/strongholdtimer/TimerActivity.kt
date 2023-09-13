package net.engdy.strongholdtimer

import android.media.MediaPlayer
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
        if (resBackgroundSound > 0) {
            Log.d(TAG, "Playing background sound $resBackgroundSound")
            playerBackground = MediaPlayer.create(this, resBackgroundSound)
            playerBackground?.isLooping = true
            playerBackground?.start()
        }
    }

    fun setSoundAtTime(res: Int, secs: Int) {
        resSoundAtTime.put(secs, res)
    }

    fun reset() {
        Log.d(TAG, "reset()")
    }

    fun onTick(millis: Long) {

    }

    fun onFinish() {

    }

    companion object {
        val TAG = TimerActivity::class.simpleName
    }
}