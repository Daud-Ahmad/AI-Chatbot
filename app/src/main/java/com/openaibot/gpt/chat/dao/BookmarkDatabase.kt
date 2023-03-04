package com.openaibot.gpt.chat.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.openaibot.gpt.chat.models.HistoryModel


@Database(entities = [HistoryModel::class], version = 1)
abstract class  BookmarkDatabase : RoomDatabase() {

    abstract fun userDao(): BookmarkDao

    companion object{
        @Volatile
        private var INSTANCE: BookmarkDatabase? = null

        fun getDatabase(context: Context): BookmarkDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookmarkDatabase::class.java,
                    "bookmark_database"
                ).fallbackToDestructiveMigration().
                build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}