package com.live.tasknew.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Course::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): CourseDao?

    companion object {

        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "dbname1.db"
                ).allowMainThreadQueries().build()
            }
            return instance
        }

    }
}