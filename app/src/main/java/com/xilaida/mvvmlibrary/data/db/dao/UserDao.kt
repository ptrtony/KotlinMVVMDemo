package com.xilaida.mvvmlibrary.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xilaida.mvvmlibrary.data.db.entity.CURRENT_USER_ID
import com.xilaida.mvvmlibrary.data.db.entity.LoginEntity

/**
@author cjq
@Date 6/9/2020
@Time 10:57 AM
@Describe:
 */

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upInsert(userEntity:LoginEntity):Long

    @Query("SELECT * FROM loginentity WHERE userId=$CURRENT_USER_ID")
    fun update():LiveData<LoginEntity>
}