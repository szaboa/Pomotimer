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

/**
 * An immutable entity that represents the screen view state of [PomotimerScreen].
 */
data class ViewState(
    val isRunning: Boolean = false,
    val showBreakDialog: Boolean = false,
    val timeMillis: Long = 0,
    val timeFrameMillis: Long = 0,
    val pomodoroCount: Int = 0,
    val pomodoroMaxCount: Int = 4,
    val roundCount: Int = 0,
    val roundMaxCount: Int = 12
)

fun ViewState.getRemainingMinutes(): Long {
    return timeMillis / 1000 / 60
}

fun ViewState.getRemainingSeconds(): Long {
    return (timeMillis / 1000) % 60
}

fun ViewState.getNextPomodoroRound(): Pair<Int, Int> {
    return if (roundCount == roundMaxCount) {
        Pair(0, 0)
    } else {
        if (pomodoroCount == pomodoroMaxCount) {
            Pair(0, roundCount + 1)
        } else {
            Pair(pomodoroCount + 1, roundCount)
        }
    }
}

fun ViewState.isPomodoroFinished(): Boolean {
    return pomodoroCount > 0
}

fun ViewState.isRoundFinished(): Boolean {
    return pomodoroCount == 0 && roundCount > 0
}

fun ViewState.isSessionFinished(): Boolean {
    return roundCount == roundMaxCount
}
