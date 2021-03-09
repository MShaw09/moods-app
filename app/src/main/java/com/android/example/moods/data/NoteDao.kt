package com.android.example.moods.data

import androidx.lifecycle.LiveData
import androidx.room.*
import java.sql.RowId
import java.time.temporal.ValueRange

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table")
    fun getAllNotes(): LiveData<List<Note>>

    @Update
    suspend fun updateNote(note: Note)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}