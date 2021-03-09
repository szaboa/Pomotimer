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

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * MVVM view model of the [PomotimerScreen] that contains the business logic.
 */
class PomotimerViewModel : ViewModel() {

    companion object {
        private const val UPDATE_FREQ_MILLIS = 1 * 1000L // 1 second
        private const val DEFAULT_TIME_FRAME_MILLIS = 10 * 60 * 1000L // 10 minutes
    }

    private var timer: CountDownTimer? = null

    /** Inner view state that is mutable. **/
    private val _viewState: MutableLiveData<ViewState> = MutableLiveData(
        ViewState(
            timeMillis = DEFAULT_TIME_FRAME_MILLIS,
            timeFrameMillis = DEFAULT_TIME_FRAME_MILLIS
        )
    )

    /** Exposed immutable view state that can be observed. */
    val viewState: LiveData<ViewState> = _viewState

    /**
     * Start a new session with the given [timeFrameMillis].
     */
    fun startSession(timeFrameMillis: Long) {
        // TODO find a more elegant solution of updating the inner view state.
        _viewState.value = _viewState.value?.copy(
            isRunning = false,
            showBreakDialog = false,
            timeMillis = timeFrameMillis,
            timeFrameMillis = timeFrameMillis,
            pomodoroCount = 0,
            roundCount = 0
        )
        cancelTimer()
    }

    /**
     * Resume or pause the timer based on its current state.
     */
    fun onPlayClicked() {
        // Update state.
        val isRunningNow = _viewState.value?.isRunning ?: false
        // TODO find a more elegant solution of updating the inner view state.
        _viewState.value = _viewState.value?.copy(
            isRunning = !isRunningNow,
            showBreakDialog = false
        )
        // Resume or pause the timer.
        if (isRunningNow) {
            cancelTimer()
        } else {
            startTimer(_viewState.value?.timeMillis ?: DEFAULT_TIME_FRAME_MILLIS)
        }
    }

    private fun startTimer(millis: Long) {
        timer = object : CountDownTimer(millis, UPDATE_FREQ_MILLIS) {

            override fun onTick(millisUntilFinished: Long) {
                // TODO find a more elegant solution of updating the inner view state.
                _viewState.value = _viewState.value?.copy(timeMillis = millisUntilFinished)
            }

            override fun onFinish() {
                // TODO find a more elegant solution of updating the inner view state.
                val (round, goal) = _viewState.value?.getNextPomodoroRound() ?: Pair(0, 0)
                _viewState.value = _viewState.value?.copy(
                    isRunning = false,
                    showBreakDialog = true,
                    timeMillis = _viewState.value?.timeFrameMillis ?: DEFAULT_TIME_FRAME_MILLIS,
                    pomodoroCount = round,
                    roundCount = goal
                )
            }
        }.start()
    }

    private fun cancelTimer() {
        timer?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        cancelTimer()
    }
}
