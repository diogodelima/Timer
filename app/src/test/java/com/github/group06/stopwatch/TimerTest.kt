package com.github.group06.stopwatch

import com.github.group06.stopwatch.model.Timer
import org.junit.Test

import org.junit.Assert.*

class TimerTest {

    @Test
    fun `try increment the timer when is active`() {

        var timer = Timer(isActive = true)

        assertEquals(Timer(current = 1, isActive = true), ++timer)
    }

    @Test
    fun `try increment the timer when is not active`() {

        var timer = Timer()

        assertEquals(Timer(), ++timer)
    }

    @Test
    fun `try initialize the timer with a value lower than zero`() {
        assertThrows(IllegalArgumentException::class.java) { Timer(current = -1) }
    }

    @Test
    fun `reset the timer`() {

        val timer = Timer(current = 10, isActive = true)

        assertEquals(Timer(current = 0, isActive = false), timer.reset())
    }

    @Test
    fun `stop the timer`() {

        val timer = Timer(current = 10, isActive = true)

        assertEquals(Timer(current = 10, isActive = false), timer.stop())
    }

    @Test
    fun `start the timer`() {

        val timer = Timer(current = 10, isActive = false)

        assertEquals(Timer(current = 10, isActive = true), timer.start())
    }

}