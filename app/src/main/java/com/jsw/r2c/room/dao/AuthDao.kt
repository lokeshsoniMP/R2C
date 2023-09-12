package com.jsw.r2c.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jsw.r2c.room.entity.UserEntity

@Dao
interface AuthDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)

    @Query("Select * from Users where id =:id")
    fun getUser(id: String): UserEntity


}