package com.example.odoo.di

import android.content.Context
import androidx.room.Room
import com.example.odoo.data.local.TimerDao
import com.example.odoo.data.local.TimerDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TimerDb = Room.databaseBuilder(
        context, TimerDb::class.java, "todo_db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideToDoDao(db: TimerDb): TimerDao = db.getTimerDao()

}