/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.pomotimer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.components.PomotimerAppBar
import com.example.androiddevchallenge.components.PomotimerClock
import com.example.androiddevchallenge.components.PomotimerControl
import com.example.androiddevchallenge.components.PomotimerCounter
import com.example.androiddevchallenge.components.PomotimerDialog
import com.example.androiddevchallenge.components.PomotimerTimePicker

/**
 * The main composable that represents the whole screen.
 */
@Composable
fun PomotimerScreen(pomotimerViewModel: PomotimerViewModel) {
    val viewState by pomotimerViewModel.viewState.observeAsState()

    PomotimerDialog(
        viewState = viewState,
        onConfirmed = {
            pomotimerViewModel.onPlayClicked()
        }
    )

    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
        Column {
            // Top appbar.
            PomotimerAppBar()
            PomotimerSpacer()
            // Clock.
            PomotimerClock(viewState = viewState)
            PomotimerSpacer()
            // Timeframe picker.
            PomotimerTimePicker(
                viewState = viewState,
                onTimePicked = { timeFrame ->
                    pomotimerViewModel.startSession(timeFrame)
                }
            )
            PomotimerSpacer()
            // Play/pause control.
            PomotimerControl(
                viewState = viewState,
                onPlayClicked = {
                    pomotimerViewModel.onPlayClicked()
                }
            )
            // Progress counter.
            PomotimerSpacer()
            PomotimerCounter(viewState = viewState)
        }
    }
}

@Composable
fun PomotimerSpacer() {
    Spacer(modifier = Modifier.height(42.dp))
}
