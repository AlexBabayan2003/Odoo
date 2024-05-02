package com.example.odoo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.odoo.data.entity.Timer

@Database(
    entities = [Timer::class],
    version = 3
)
abstract class TimerDb: RoomDatabase() {
    abstract fun getTimerDao(): TimerDao
}