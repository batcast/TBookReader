package com.example.tbookreader.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class BookGroup(
        @PrimaryKey
        val groupId: Int = 0b1,
        var groupName: String,
        var order: Int = 0
) : Parcelable