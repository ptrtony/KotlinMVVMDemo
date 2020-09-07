package com.xilaida.mvvmlibrary.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xilaida.mvvmlibrary.data.db.dao.UserDao
import com.xilaida.mvvmlibrary.data.db.entity.LoginEntity

/**
@author cjq
@Date 6/9/2020
@Time 10:31 AM
@Describe:
 */

@Database(entities = [LoginEntity::class],version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getUserDao(): UserDao

    companion object{

        @Volatile
        private var instance:AppDatabase?=null
        private val LOCK = Any()

        operator fun invoke(context:Context):AppDatabase{
            return instance?: synchronized(LOCK){
                instance?:buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context):AppDatabase{
            return instance?:Room
                .databaseBuilder(context,AppDatabase::class.java,"appdatabase.db")
                .build()
        }
    }
}