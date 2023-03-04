package com.openaibot.gpt.chat.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.openaibot.gpt.chat.models.HistoryModel


@Dao
interface BookmarkDao {

    @Query("SELECT * FROM historyModel")
    fun getAllDua(): List<HistoryModel>

    @Insert
    fun insertDua(duaDataModel: HistoryModel)

    @Delete
    fun deleteDua(duaDataModel: HistoryModel)

    @Query("DELETE FROM historyModel")
    fun deleteAllDua()
}