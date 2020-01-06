package com.example.movieapplication.model

import android.app.Notification
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
@Entity
data class Movie (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long? = null,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "rating") var rating: String,
    @ColumnInfo(name = "type") var type: String,
    @ColumnInfo(name = "notification") var notification: Date? = null,
    @ColumnInfo(name = "notes") var notes: String? = null
) : Parcelable