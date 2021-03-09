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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import java.util.concurrent.TimeUnit

@Composable
fun PomotimerTimePicker(viewState: ViewState?, onTimePicked: (Long) -> Unit) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(getTimeFrames()) { timeFrame ->
            PomotimerTimeCell(timeFrame, viewState, onTimePicked)
        }
    }
}

@Composable
fun PomotimerTimeCell(timeFrame: Int, viewState: ViewState?, onTimePicked: (Long) -> Unit) {
    val timeFrameMillis = TimeUnit.MINUTES.toMillis(timeFrame.toLong())
    val isActive = viewState?.timeFrameMillis == timeFrameMillis
    Box(
        modifier = Modifier
            .size(60.dp)
            .border(
                BorderStroke(
                    width = 3.dp,
                    color = getColor(isActive, Color.White, MaterialTheme.colors.primaryVariant)
                )
            )
            .background(getColor(isActive, Color.White, Color.Transparent))
            .clip(shape = MaterialTheme.shapes.small)
            .clickable { onTimePicked(timeFrameMillis) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = timeFrame.toString(),
            color = getColor(isActive, MaterialTheme.colors.primary, Color.White),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )
    }
}

private fun getColor(isActive: Boolean, activeColor: Color, nonActiveColor: Color): Color {
    return if (isActive) {
        activeColor
    } else {
        nonActiveColor
    }
}

private fun getTimeFrames(): List<Int> {
    return listOf(1, 5, 10, 15, 20, 25, 30, 35, 40, 45)
}
