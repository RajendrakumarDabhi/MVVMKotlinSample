package com.example.ravi.mvvmsample.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database
import com.example.ravi.mvvmsample.db.dao.UserDao
import com.example.ravi.mvvmsample.models.User


@Database(entities = [User::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun UserDao(): UserDao

    companion object {

        private val DB_NAME: String="mvvmsampledb"

        @Volatile
        private var INSTANCE: LocalDatabase? = null

        internal fun getDatabase(context: Context): LocalDatabase? {
            if (INSTANCE == null) {
                synchronized(LocalDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            LocalDatabase::class.java, DB_NAME
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}