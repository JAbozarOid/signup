package com.monstarlab.signup.di.module

import android.content.Context
import androidx.room.Room
import com.monstarlab.data.db.database.RoutineDatabase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    fun provideAppDatabase(@ApplicationContext context:Context) : RoutineDatabase{
        return Room.databaseBuilder(
            context, RoutineDatabase::class.java, "routine_db"
        ).fallbackToDestructiveMigration().build()
    }

}