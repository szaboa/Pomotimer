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

import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.pomotimer.ViewState
import com.example.androiddevchallenge.pomotimer.isPomodoroFinished
import com.example.androiddevchallenge.pomotimer.isRoundFinished
import com.example.androiddevchallenge.pomotimer.isSessionFinished

@Composable
fun PomotimerDialog(viewState: ViewState?, onConfirmed: () -> Unit) {
    viewState?.let {
        if (!viewState.showBreakDialog) {
            return
        }
        when {
            viewState.isSessionFinished() -> {
                PomotimerDialogContent(
                    title = "Congrats!",
                    text = "You've finished a session.",
                    onConfirmed = onConfirmed
                )
            }
            viewState.isRoundFinished() -> {
                PomotimerDialogContent(
                    title = "Take a break!",
                    text = "You've finished a round, take a longer break.",
                    onConfirmed = onConfirmed
                )
            }
            viewState.isPomodoroFinished() -> {
                PomotimerDialogContent(
                    title = "Take a break!",
                    text = "You've finished a pomodoro, take a short break.",
                    onConfirmed = onConfirmed
                )
            }
        }
    }
}

@Composable
fun PomotimerDialogContent(title: String, text: String, onConfirmed: () -> Unit) {
    AlertDialog(
        modifier = Modifier.padding(8.dp),
        backgroundColor = Color.White,
        onDismissRequest = {},
        confirmButton = {
            Button(
                onClick = {
                    onConfirmed()
                }
            ) {
                Text(
                    text = "Continue",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6
                )
            }
        },
        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h5
            )
        },
        text = {
            Text(
                text = text,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6
            )
        }
    )
}
