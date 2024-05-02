package com.example.odoo.repo

import com.example.odoo.data.entity.Timer
import kotlinx.coroutines.flow.Flow

interface TimerRepository {

    fun createTimer(timer: Timer)

    fun getAllTimers(): Flow<List<Timer>>

    fun updateTimer(timer: Timer)

}