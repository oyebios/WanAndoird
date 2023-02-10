package com.lw.mvvm.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun <T : RoomDatabase> createDB(applicationContext: Context,clazz: Class<T>,name : String) : T{
    return Room.databaseBuilder(applicationContext, clazz, name).build()
}