package com.monstarlab.signup.di.module

import com.monstarlab.signup.view.dialog.LoadingDialog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideLoading() = LoadingDialog()

}