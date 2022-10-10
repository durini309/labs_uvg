package com.durini.solucionlab10.data.local.dao

import androidx.room.*
import com.durini.solucionlab10.data.local.model.Character
import com.durini.solucionlab10.data.remote.dto.CharacterDto

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
    suspend fun getCharacters(): List<Character>

    @Query("SELECT * FROM character WHERE id = :id")
    suspend fun getCharacter(id: Int): Character?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Character>)

    @Update
    suspend fun update(character: Character)

    @Delete
    suspend fun delete(character: Character): Int

    @Query("DELETE FROM character")
    suspend fun deleteAll(): Int

}