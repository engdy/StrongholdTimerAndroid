package net.engdy.strongholdtimer

import android.os.CountDownTimer
import android.util.Log
import android.util.SparseIntArray

class SHGTimer(
    val duration: Long,
    val interval: Long,
    val tick: (Long) -> Unit = { Log.d(TAG, "tick") },
    val finish: () -> Unit = { Log.d(TAG, "finished") }
) {
    private var timer: CountDownTimer? = null
    private var currentDuration = duration

    fun start() {
        timer = createTimer()
        timer?.start()
    }

    fun cancel() {
        timer?.cancel()
        timer = null
    }

    fun reset() {
        timer?.cancel()
        timer = null
        currentDuration = duration
    }

    private fun createTimer(): CountDownTimer {
        return object: CountDownTimer(currentDuration, interval) {
            override fun onTick(millisUntilFinished: Long) {
                currentDuration = millisUntilFinished
                tick(millisUntilFinished)
            }

            override fun onFinish() {
                finish()
            }
        }
    }

    companion object {
        val TAG = SHGTimer::class.simpleName
    }
}
