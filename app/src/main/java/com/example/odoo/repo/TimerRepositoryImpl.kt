package com.example.odoo.repo

import com.example.odoo.data.entity.Timer
import com.example.odoo.data.local.TimerDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TimerRepositoryImpl @Inject constructor(
    private val dao: TimerDao,
) : TimerRepository {
    override fun createTimer(timer: Timer) {
        dao.insertTimer(timer)
    }

    override fun getAllTimers(): Flow<List<Timer>> =
        dao.getAllTimers()

    override fun updateTimer(timer: Timer) {
        dao.updateTimer(timer)
    }

}