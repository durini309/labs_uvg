package com.durini.architecture.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.durini.architecture.data.local.dao.CharacterDao
import com.durini.architecture.data.local.entity.Character

@Database(
    entities = [Character::class],
    version = 1
)
abstract class RickAndMortyDb : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}