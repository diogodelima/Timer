package com.github.group06.stopwatch.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TimerSaver(

    val current: Int,
    val isActive: Boolean

): Parcelable