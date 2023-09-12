package com.jsw.r2c.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jsw.r2c.room.dao.AuthDao
import com.jsw.r2c.room.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun authDao(): AuthDao

    companion object {
        const val DATABASE_NAME = "r2c_db"
    }

}