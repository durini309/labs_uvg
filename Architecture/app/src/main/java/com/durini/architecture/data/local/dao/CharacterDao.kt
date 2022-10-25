package com.durini.architecture.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.durini.architecture.data.local.entity.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
    suspend fun getCharacters(): List<Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Character>)

}