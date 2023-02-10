package com.lw.mvvm.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "user") val user: String?,
    @ColumnInfo(name = "password") val password: String?
)