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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.pomotimer.ViewState

@Composable
fun PomotimerControl(viewState: ViewState?, onPlayClicked: () -> Unit) {
    val sweepAngle = getSweepAngle(viewState)
    val innerCircleColor = colors.secondary
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Canvas(
            modifier = Modifier.size(120.dp),
            onDraw = {
                drawCircle(
                    color = innerCircleColor,
                    radius = 128f
                )
                drawArc(
                    color = Color.White,
                    startAngle = -90f,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(width = 20f, cap = StrokeCap.Round)
                )
            }
        )
        IconButton(onClick = { onPlayClicked() }) {
            Icon(
                imageVector = getIcon(viewState),
                contentDescription = "play-button",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

fun getIcon(viewState: ViewState?): ImageVector {
    if (viewState == null) {
        return Icons.Filled.Pause
    }
    return if (viewState.isRunning) {
        Icons.Filled.Pause
    } else {
        Icons.Filled.PlayArrow
    }
}

fun getSweepAngle(viewState: ViewState?): Float {
    if (viewState == null) {
        return 0f
    }
    val elapsedMillis = viewState.timeFrameMillis - viewState.timeMillis
    return (elapsedMillis * 100 / viewState.timeFrameMillis) * 360 / 100f
}
