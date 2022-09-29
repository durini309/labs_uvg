package com.durini.roomdemo.data.local_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.durini.roomdemo.domain.model.User

@Database(entities = [User::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao
}