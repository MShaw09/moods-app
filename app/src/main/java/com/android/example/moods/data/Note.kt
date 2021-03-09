/*
Do not need to change the Note Object unless you want to add additional functionality

The Note Class stores all the information necessary for storing a Mood Note into the Database as well
as the information required to pass data across fragments with safe args in Jetpack Navigation.
*/

package com.android.example.moods.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title: String?,
    var content: String?,
    var reaction: String?
) : Parcelable