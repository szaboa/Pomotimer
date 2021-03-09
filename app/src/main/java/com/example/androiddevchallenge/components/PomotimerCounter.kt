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
package com.example.androiddevchallenge.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.androiddevchallenge.pomotimer.ViewState

@Composable
fun PomotimerCounter(viewState: ViewState?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        PomotimerCounterRound(viewState)
        PomotimerCounterGoal(viewState)
    }
}

@Composable
fun PomotimerCounterRound(viewState: ViewState?) {
    PomotimerCounterBase(
        text = "Pomodoro",
        value = viewState?.pomodoroCount ?: 0,
        maxValue = viewState?.pomodoroMaxCount ?: 0
    )
}

@Composable
fun PomotimerCounterGoal(viewState: ViewState?) {
    PomotimerCounterBase(
        text = "Round",
        value = viewState?.roundCount ?: 0,
        maxValue = viewState?.roundMaxCount ?: 0
    )
}

@Composable
fun PomotimerCounterBase(text: String, value: Int, maxValue: Int) {
    Column {
        Text(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            text = "$value/$maxValue",
            color = MaterialTheme.colors.primaryVariant,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )
        Text(
            text = text,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )
    }
}
