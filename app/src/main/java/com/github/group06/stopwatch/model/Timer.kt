package com.github.group06.stopwatch.model

import android.annotation.SuppressLint

data class Timer(

    val current: Int = 0,
    val isActive: Boolean = false

) {

    init {
        require(current >= 0) { "The start value should be zero or positive." }
    }

    operator fun inc(): Timer {

        if (isActive)
            return Timer(current = current + 1, isActive = true)

        return this
    }

    fun reset() = Timer()

    fun stop() = Timer(current = current, isActive = false)

    fun start() = Timer(current = current, isActive = true)

    override fun toString(): String {
        return formatTime(current)
    }

}

@SuppressLint("DefaultLocale")
fun formatTime(seconds: Int): String {

    val hours = (seconds / 3600)
    var remaining = seconds % 3600
    val minutes = remaining / 60
    remaining %= 60

    return String.format("%02d:%02d:%02d", hours, minutes, remaining)
}