package com.jsw.r2c.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.jsw.r2c.base.JswApplication
import com.jsw.r2c.retrofit.RetrofitHelper
import com.jsw.r2c.retrofit.networkApi.AuthAPIService
import com.jsw.r2c.retrofit.networkApi.RequisitionAPIService
import com.jsw.r2c.room.AppDataBase
import com.jsw.r2c.room.dao.AuthDao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideApplication(): Application = JswApplication()

    //


    @Provides
    @Singleton
    fun provideMaterialAPIService(): RequisitionAPIService =
        RetrofitHelper.getInstance(RequisitionAPIService::class.java)

    @Provides
    @Singleton
    fun provideAuthAPIService(): AuthAPIService =
        RetrofitHelper.getInstance(AuthAPIService::class.java)


    @Provides
    @Singleton
    fun provideRoomDB(@ApplicationContext context: Context): AppDataBase = Room.run {
        databaseBuilder(
            context = context,
            AppDataBase::class.java,
            AppDataBase.DATABASE_NAME
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: AppDataBase): AuthDao {
        return database.authDao()
    }


}