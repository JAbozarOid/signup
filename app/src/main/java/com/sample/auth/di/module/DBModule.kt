package com.sample.auth.di.module

import android.content.Context
import androidx.room.Room
import com.sample.data.db.database.AppDatabase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    fun provideAppDatabase(@ApplicationContext context:Context) : AppDatabase{
        return Room.databaseBuilder(
            context, AppDatabase::class.java, "routine_db"
        ).fallbackToDestructiveMigration().build()
    }

}