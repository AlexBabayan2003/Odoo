package com.example.odoo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.odoo.data.entity.Timer
import kotlinx.coroutines.flow.Flow


@Dao
interface TimerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTimer(timer: Timer)

    @Query("SELECT * FROM Timer")
    fun getAllTimers(): Flow<List<Timer>>

    @Update
    fun updateTimer(timer: Timer)


}