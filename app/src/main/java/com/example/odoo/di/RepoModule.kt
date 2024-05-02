package com.example.odoo.di

import com.example.odoo.repo.TimerRepository
import com.example.odoo.repo.TimerRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun bindTimerRepository(
        repo: TimerRepositoryImpl
    ): TimerRepository

}