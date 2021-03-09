package com.android.example.moods.data

import android.content.Context
import android.view.ContextThemeWrapper
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun NoteDao() : NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            val tempNoteInstance = INSTANCE
            if(tempNoteInstance != null) {
                return tempNoteInstance
            }
            synchronized(this) {
                val noteInstance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = noteInstance
                return noteInstance
            }
        }
    }
}