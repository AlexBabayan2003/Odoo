package com.example.odoo

import androidx.lifecycle.ViewModel
import com.example.odoo.data.entity.Timer
import com.example.odoo.repo.TimerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(
    private val repository: TimerRepository
) : ViewModel() {

    val getTimers = repository.getAllTimers()
    fun createTimer(timer: Timer) = repository.createTimer(timer)

    fun updateTimer(timer: Timer, timeInSeconds: Int) {
        timer.time = timeInSeconds
        repository.updateTimer(timer)
    }

}