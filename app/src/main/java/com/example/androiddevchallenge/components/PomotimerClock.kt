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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.pomotimer.ViewState
import com.example.androiddevchallenge.pomotimer.getRemainingMinutes
import com.example.androiddevchallenge.pomotimer.getRemainingSeconds

@Composable
fun PomotimerClock(viewState: ViewState?) {
    Row {
        Spacer(
            modifier = Modifier.width(36.dp)
        )
        PomotimerClockBox(
            modifier = Modifier.weight(1f),
            value = viewState?.getRemainingMinutes() ?: 0
        )
        PomotimerClockDivider(
            modifier = Modifier.weight(0.2f)
        )
        PomotimerClockBox(
            modifier = Modifier.weight(1f),
            value = viewState?.getRemainingSeconds() ?: 0
        )
        Spacer(
            modifier = Modifier.width(36.dp)
        )
    }
}

@Composable
fun PomotimerClockBox(modifier: Modifier, value: Long) {
    Box(
        modifier = modifier
            .height(160.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (value < 10) "0$value" else "$value",
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
fun PomotimerClockDivider(modifier: Modifier) {
    Box(
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp)
            .height(160.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = ":",
            color = Color.White,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3
        )
    }
}
