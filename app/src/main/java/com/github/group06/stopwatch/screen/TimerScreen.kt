package com.github.group06.stopwatch.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.github.group06.stopwatch.R
import com.github.group06.stopwatch.model.Timer
import com.github.group06.stopwatch.model.TimerSaver
import kotlinx.coroutines.delay

const val START_BUTTON_TAG = "start_button"
const val STOP_BUTTON_TAG = "stop_button"
const val RESET_BUTTON_TAG = "reset_button"
fun Timer.toTestTag() = "timer_$current"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerScreen(startTimer: Timer = Timer()) {

    var timer: Timer by rememberSaveable(saver = saver) { mutableStateOf(startTimer) }
    val delay = 1000L

    LaunchedEffect(timer) {

        while (timer.isActive){
            delay(delay)
            timer++
        }

    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
            )
        }
    ) { innerPadding ->

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.testTag(timer.toTestTag()),
                text = timer.toString(),
                fontSize = TextUnit(12f, TextUnitType.Em)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                CustomButton(
                    modifier = Modifier.testTag(START_BUTTON_TAG),
                    text = stringResource(R.string.start_text),
                    enabled = !timer.isActive
                ) {
                    timer = timer.start()
                }

                CustomButton(
                    modifier = Modifier.testTag(STOP_BUTTON_TAG),
                    text = stringResource(R.string.stop_text),
                    enabled = timer.isActive
                ) {
                    timer = timer.stop()
                }

                CustomButton(
                    modifier = Modifier.testTag(RESET_BUTTON_TAG),
                    text = stringResource(R.string.reset_text)
                ) {
                    timer = timer.reset()
                }

            }

        }

    }

}

@Composable
fun CustomButton(modifier: Modifier = Modifier, text: String, enabled: Boolean = true, onClick: () -> Unit) {

    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled
    ) {
        Text(text = text)
    }

}

private val saver = Saver<MutableState<Timer>, TimerSaver>(
    save = { onSave ->
        val timer = onSave.value
        TimerSaver(current = timer.current, isActive = timer.isActive)
    },
    restore = { onRestore ->
        mutableStateOf(Timer(current = onRestore.current, isActive = onRestore.isActive))
    }
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TimerScreenPreview() {
    TimerScreen()
}